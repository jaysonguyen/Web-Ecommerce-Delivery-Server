package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.chart.ChartDTO;
import com.example.deliveryecommercebackend.DTO.chart.DataQuery;
import com.example.deliveryecommercebackend.DTO.chart.DatasetDTO;
import com.example.deliveryecommercebackend.DTO.report.ProductGetList;
import com.example.deliveryecommercebackend.model.*;
import com.example.deliveryecommercebackend.model.product.OrderProduct;
import com.example.deliveryecommercebackend.model.product.ProductComponent;
import com.example.deliveryecommercebackend.model.product.ProductItem;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import com.example.deliveryecommercebackend.repository.CityRepository;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import com.example.deliveryecommercebackend.repository.ProductTypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ActionRepository actionRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    public ResponseEntity<?> orderReport(DateRange dateRange, String cityCode) {
        try {
            ChartDTO res = new ChartDTO();

            //get labels === order action
            List<Action> actions = actionRepository.findAll();
            if(actions == null) {
                return ResponseEntity.ok().body(Collections.emptyList());
            }
            //get action's name list
            ArrayList<String> labels = new ArrayList<>();
            ArrayList<String> labelsNumber = new ArrayList<>();
            for (var item : actions) {
                labels.add(item.getName());
                labelsNumber.add(item.getCode());
            }

            //get order list
            List<DataQuery> temp = orderRepository.getSumOrderByDateAndAction(dateRange.getStart(), dateRange.getEnd(), cityCode);
            // sort list by action_code
            temp = temp.stream()
                    .sorted(Comparator.comparing(DataQuery::getGroup))
                    .collect(Collectors.toList());
            res = makeChartData(dateRange, temp, labelsNumber);
            res.setLabels(labels);

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get order report - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error at service - Error: " + ex.getMessage());
        }
    }

    private static Map<Date, Map<String, Integer>> getCountByDateAndType(List<ProductGetList> productDataList) {
        Map<Date, Map<String, Integer>> countByDateAndType = new HashMap<>();

        for (ProductGetList productData : productDataList) {
            Date createDate = productData.getCreate();
            ProductItem productItem = (ProductItem) productData.getProduct().getProductItemList().get(0);
            String productType = productItem.getProduct_type();

            countByDateAndType.computeIfAbsent(createDate, k -> new HashMap<>());
            countByDateAndType.get(createDate).merge(productType, 1, Integer::sum);
        }

        return countByDateAndType;
    }

    public static List<DataQuery> mapToProductCountList(Map<Date, Map<String, Integer>> countByDateAndType) throws ParseException {
        List<DataQuery> result = new ArrayList<>();

        for (Map.Entry<Date, Map<String, Integer>> entry : countByDateAndType.entrySet()) {
            Date date = entry.getKey();
            Map<String, Integer> typeCountMap = entry.getValue();

            for (Map.Entry<String, Integer> typeCountEntry : typeCountMap.entrySet()) {
                String productType = typeCountEntry.getKey();
                int count = typeCountEntry.getValue();

                DataQuery productCount = new DataQuery(count, date, productType);
                result.add(productCount);
            }
        }

        return result;
    }

    private ChartDTO makeChartData(DateRange dateRange, List<DataQuery> temp, List<String> labels) {
        ChartDTO res = new ChartDTO();

        // 6 actions
        ArrayList<DatasetDTO> dataSetList = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("MM-dd-yyyy");
        Date dateStart = dateRange.getStart();
        String currDate = dt.format(dateStart);

        //set values
        //sum all value that have same date and action_code
        while(dateStart.before(dateRange.getEnd()) || dateStart.equals(dateRange.getEnd())){
            // get dataset label: Date, values length = 6 => each value present 1 action quantity
            DatasetDTO dataSet = new DatasetDTO();
            //set key
            dataSet.setKey(currDate);
            //list sum by date
            ArrayList<Long> sumByDate = new ArrayList<>();
            // loop through action_code list
            for(int i = 0; i < labels.size(); i++){
                long sum = 0;
                //sum by action_code and date
                for(var item : temp){
                    if(dt.format(item.getLabel()).equals(dt.format(dateStart)) &&
                            item.getGroup().equals(labels.get(i))) {
                        sum += item.getCounts();
                    }
                }
                //push sum to list
                sumByDate.add(sum);
            }
            //set values
            dataSet.setValues(sumByDate);
            dataSetList.add(dataSet);
            //add 1 day into date
            c.setTime(dateStart);
            c.add(Calendar.DATE, 1);
            dateStart = c.getTime();
            currDate = dt.format(dateStart);
        }

        res.setPoints(dataSetList);
        return res;
    }

    public ResponseEntity<?> productTypeReport(DateRange dateRange) {
        try {
            ChartDTO res = new ChartDTO();

            //get labels === order action
            List<ProductType> productTypes = productTypeRepository.findAll();
            if(productTypes.isEmpty()) {
                return ResponseEntity.ok().body(Collections.emptyList());
            }
            //get action's name list
            ArrayList<String> labels = new ArrayList<>();
            for (var item : productTypes) {
                labels.add(item.getName());
            }

            //get product list
            List<ProductGetList> productList = orderRepository.getProductListOfallOrder(dateRange.getStart(), dateRange.getEnd());
            Map<Date, Map<String, Integer>> check = getCountByDateAndType(productList);

            List<DataQuery> temp = mapToProductCountList(check);

            res = makeChartData(dateRange, temp, labels);
            res.setLabels(labels);

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get order report - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error at service - Error: " + ex.getMessage());
        }
    }
}

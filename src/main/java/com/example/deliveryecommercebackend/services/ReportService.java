package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.chart.ChartDTO;
import com.example.deliveryecommercebackend.DTO.chart.DataQuery;
import com.example.deliveryecommercebackend.DTO.chart.DatasetDTO;
import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.model.City;
import com.example.deliveryecommercebackend.model.DateRange;
import com.example.deliveryecommercebackend.model.Order;
import com.example.deliveryecommercebackend.repository.ActionRepository;
import com.example.deliveryecommercebackend.repository.CityRepository;
import com.example.deliveryecommercebackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ActionRepository actionRepository;
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
            for (var item : actions) {
                labels.add(item.getName());
            }
            res.setLabels(labels);

            //get order list
            List<DataQuery> temp = orderRepository.getSumOrderByDateAndAction(dateRange.getStart(), dateRange.getEnd(), cityCode);
            // sort list by action_code
            temp = temp.stream()
                    .sorted(Comparator.comparing(DataQuery::getGroup))
                    .collect(Collectors.toList());

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
                for(int i = 0; i <= 6; i++){
                    long sum = 0;
                    //sum by action_code and date
                    for(var item : temp){
                        if(dt.format(item.getLabel()).equals(dt.format(dateStart)) &&
                                item.getGroup().equals(i + "")) {
                            sum += item.getCounts();
                        }
                        //System.out.println(item.getGroup() + " - " + dt.format(item.getLabel()) + " - " + item.getCounts());
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

            return ResponseEntity.ok().body(res);
        } catch(Exception ex) {
            System.out.printf("Get order report - Error: " + ex.getMessage());
            return ResponseEntity.badRequest().body("Error at service - Error: " + ex.getMessage());
        }
    }
}

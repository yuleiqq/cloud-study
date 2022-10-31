//package com.example.camundademo1.tt;
//
//import org.camunda.bpm.client.ExternalTaskClient;
//
//import java.awt.*;
//import java.net.URI;
//import java.util.logging.Logger;
//
//
///**
// *
// * payment-retrival:1:5007b865-3ed6-11ed-bf2b-22311f914cef
// *
// */
//
//public class ChargeCardWorker {
//  private final static Logger LOGGER = Logger.getLogger(ChargeCardWorker.class.getName());
//
//  public static void main(String[] args) {
//    ExternalTaskClient client = ExternalTaskClient.create()
//        .baseUrl("http://node1:8080/engine-rest")
//        .asyncResponseTimeout(10000) // long polling timeout
//        .build();
//
//    // subscribe to an external task topic as specified in the process
//    client.subscribe("charge-card")
//        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
//        .handler((externalTask, externalTaskService) -> {
//          // Put your business logic here
//
//          // Get a process variable
//          String item = externalTask.getVariable("item");
//          Integer amount = externalTask.getVariable("amount");
//
//          LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");
//
//          try {
//              Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
//          } catch (Exception e) {
//              e.printStackTrace();
//          }
//
//          // Complete the task
//          externalTaskService.complete(externalTask);
//        })
//        .open();
//  }
//}

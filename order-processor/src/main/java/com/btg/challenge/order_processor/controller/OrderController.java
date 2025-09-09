package com.btg.challenge.order_processor.controller;

import com.btg.challenge.order_processor.controller.wrapper.ApiResponse;
import com.btg.challenge.order_processor.dto.output.clientOrder.ClientOrderDTO;
import com.btg.challenge.order_processor.dto.output.ClientOrderCount;
import com.btg.challenge.order_processor.dto.output.TotalOrderAmount;
import com.btg.challenge.order_processor.service.order.output.ClientOrderCountService;
import com.btg.challenge.order_processor.service.order.output.ClientOrderService;
import com.btg.challenge.order_processor.service.order.output.TotalOrderAmountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/order")
public class OrderController {

    private final TotalOrderAmountService totalOrderAmountService;
    private final ClientOrderCountService clientOrderCountService;
    private final ClientOrderService clientOrderService;

    public OrderController (
            TotalOrderAmountService totalOrderAmountService,
            ClientOrderCountService clientOrderCountService,
            ClientOrderService clientOrderService
    ) {
        this.totalOrderAmountService = totalOrderAmountService;
        this.clientOrderCountService = clientOrderCountService;
        this.clientOrderService = clientOrderService;
    }

    @GetMapping("/totalAmount")
    public ResponseEntity<ApiResponse<List<TotalOrderAmount>>> getTotalOrdersAmount(
            @RequestParam(name = "pagina", defaultValue = "0") Integer page,
            @RequestParam(name = "tamanhoPagina", defaultValue = "10") Integer pageSize
    ) {
        var body = totalOrderAmountService.getAllPageable(PageRequest.of(page, pageSize));

        return ResponseEntity.ok(ApiResponse.ok(body));
    }

    @GetMapping("/{orderCode}/totalAmount")
    public ResponseEntity<ApiResponse<TotalOrderAmount>> getTotalOrdersAmountByOrderCode(
            @PathVariable Long orderCode
    ) {
        var body = totalOrderAmountService.getByOrderCode(orderCode);

        return ResponseEntity.ok(ApiResponse.ok(body));
    }

    @GetMapping(value = "/clients/orders/count", produces = "application/json")
    public ResponseEntity<ApiResponse<List<ClientOrderCount>>> getClientOrdersCount(
            @RequestParam(name = "pagina", defaultValue = "0") Integer page,
            @RequestParam(name = "tamanhoPagina", defaultValue = "10") Integer pageSize
    ) {

        Page<ClientOrderCount> body = clientOrderCountService.getAllPageable(PageRequest.of(page, pageSize));

        return ResponseEntity.ok(ApiResponse.ok(body));
    }

    @GetMapping(value = "/clients/{clientCode}/orders/count", produces = "application/json")
    public ResponseEntity<ApiResponse<ClientOrderCount>> getClientOrdersCountByClientCode(
            @PathVariable(name = "clientCode") Long clientCode
    ) {
        var body = clientOrderCountService.getByClientCode(clientCode);
        return ResponseEntity.ok(ApiResponse.ok(body));
    }

    @GetMapping("/clients/orders")
    public ResponseEntity<ApiResponse<List<ClientOrderDTO>>> getClientOrders () {
        var body = clientOrderService.getAll();
        return ResponseEntity.ok(ApiResponse.ok(body));
    }

    @GetMapping("/clients/{clientCode}/orders")
    public ResponseEntity<ApiResponse<ClientOrderDTO>> getClientOrdersByClientCode (
            @PathVariable Long clientCode,
            @RequestParam(name = "pagina", defaultValue = "0") Integer page,
            @RequestParam(name = "tamanhoPagina", defaultValue = "10") Integer pageSize
    ) {
        var body = clientOrderService.getByClientCode(clientCode, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(ApiResponse.ok(body, page, pageSize, body.totalElements()));
    }
}

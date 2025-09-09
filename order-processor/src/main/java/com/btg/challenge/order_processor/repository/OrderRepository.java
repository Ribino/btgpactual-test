package com.btg.challenge.order_processor.repository;

import com.btg.challenge.order_processor.entity.Order;
import com.btg.challenge.order_processor.repository.projection.ClientOrderCountProjection;
import com.btg.challenge.order_processor.repository.projection.OrderTotalAmountProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
            SELECT
                o.code as code,
                o.totalAmount as totalAmount
            FROM Order o
            """)
    Page<OrderTotalAmountProjection> findAllOrderTotalAmountProjection(Pageable page);

    <T> Optional<T> findByCode(Long code, Class<T> type);

    Page<Order> findByClientCode(Long clientCode, Pageable pageable);

    @Query("""
           SELECT
            o.clientCode as clientCode,
            COUNT(o) as totalOrders
           FROM Order o
           WHERE o.clientCode = :clientCode
           GROUP BY o.clientCode
           """)
    Optional<ClientOrderCountProjection> findClientOrderCountByClientCode(@Param("clientCode") Long clientCode);

    @Query("""
           SELECT
            o.clientCode as clientCode,
            COUNT(o) as totalOrders
           FROM Order o
           GROUP BY o.clientCode
           """)
    Page<ClientOrderCountProjection> findClientOrderCount(Pageable pageable);

}

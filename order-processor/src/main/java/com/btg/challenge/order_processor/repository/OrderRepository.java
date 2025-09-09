package com.btg.challenge.order_processor.repository;

import com.btg.challenge.order_processor.entity.Order;
import com.btg.challenge.order_processor.repository.projection.ClientOrderCountProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    <T> List<T> findByCode(Long code, Class<T> type);

    <T> List<T> findByClientCode(Long clientCode, Class<T> type);

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

    boolean existsByCode(Long code);

}

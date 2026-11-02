package com.example.multi_vendor_ecommerce_api.repositories;

import com.example.multi_vendor_ecommerce_api.models.VendorStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<VendorStore,Long> {
}

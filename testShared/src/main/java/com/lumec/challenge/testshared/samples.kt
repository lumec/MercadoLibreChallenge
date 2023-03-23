package com.lumec.challenge.testshared

import com.lumec.challenge.domain.ProductDetails
import com.lumec.challenge.domain.ProductPreview

val sampleProductList = listOf(
    ProductPreview(
        false,
        "M0000001",
        "Cali, Valle",
        "www.picture.com/M0000001.jpg",
        20000,
        "Product 1"
    ), ProductPreview(
        true,
        "M0000002",
        "Cali, Valle",
        "www.picture.com/M0000002.jpg",
        3685686,
        "Product 2"
    )
)

val sampleProductDetails = ProductDetails(
    false,
    11,
    "new",
    "This is a product",
    true,
    "M0000001",
    "Cali, Valle",
    "www.picture.com/M0000001.jpg",
    listOf("www.picture.com/M0000001.jpg", "www.picture.com/M0000001.jpg"),
    20000,
    "Product 1"
)
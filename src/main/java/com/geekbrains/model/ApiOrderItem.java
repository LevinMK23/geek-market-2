package com.geekbrains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * ApiOrderItem
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-30T21:05:27.224656+03:00[Europe/Moscow]")
public class ApiOrderItem {

  @JsonProperty("productName")
  private String productName;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("count")
  private Integer count;

  public ApiOrderItem productName(String productName) {
    this.productName = productName;
    return this;
  }

  /**
   * Get productName
   * @return productName
  */
  
  @Schema(name = "productName", required = false)
  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public ApiOrderItem price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  
  @Schema(name = "price", required = false)
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ApiOrderItem count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  */
  
  @Schema(name = "count", required = false)
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiOrderItem apiOrderItem = (ApiOrderItem) o;
    return Objects.equals(this.productName, apiOrderItem.productName) &&
        Objects.equals(this.price, apiOrderItem.price) &&
        Objects.equals(this.count, apiOrderItem.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productName, price, count);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiOrderItem {\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


package com.geekbrains.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ApiOrder
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-08-30T21:05:27.224656+03:00[Europe/Moscow]")
public class ApiOrder {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("details")
  @Valid
  private List<ApiOrderItem> details = null;

  public ApiOrder id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ApiOrder price(Double price) {
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

  public ApiOrder details(List<ApiOrderItem> details) {
    this.details = details;
    return this;
  }

  public ApiOrder addDetailsItem(ApiOrderItem detailsItem) {
    if (this.details == null) {
      this.details = new ArrayList<>();
    }
    this.details.add(detailsItem);
    return this;
  }

  /**
   * Get details
   * @return details
  */
  @Valid 
  @Schema(name = "details", required = false)
  public List<ApiOrderItem> getDetails() {
    return details;
  }

  public void setDetails(List<ApiOrderItem> details) {
    this.details = details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiOrder apiOrder = (ApiOrder) o;
    return Objects.equals(this.id, apiOrder.id) &&
        Objects.equals(this.price, apiOrder.price) &&
        Objects.equals(this.details, apiOrder.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiOrder {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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


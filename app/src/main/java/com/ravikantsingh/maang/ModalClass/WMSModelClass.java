package com.ravikantsingh.maang.ModalClass;

public class WMSModelClass {
    String recommendationDate, amount, sector, scheme, implementingAgency;

    public WMSModelClass(String recommendationDate, String amount, String sector, String scheme, String implementingAgency) {
        this.recommendationDate = recommendationDate;
        this.amount = amount;
        this.sector = sector;
        this.scheme = scheme;
        this.implementingAgency = implementingAgency;
    }

    public String getRecommendationDate() {
        return recommendationDate;
    }

    public void setRecommendationDate(String recommendationDate) {
        this.recommendationDate = recommendationDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getImplementingAgency() {
        return implementingAgency;
    }

    public void setImplementingAgency(String implementingAgency) {
        this.implementingAgency = implementingAgency;
    }
}

package com.alan.microservices.event.handler.model;

import lombok.Data;

@Data
public class OnlineSummary {
    private Long devId;
    private Long siteId;
    private Boolean online;

    private OnlineSummary(Long devId, Long siteId, Boolean online) {
        this.devId = devId;
        this.siteId = siteId;
        this.online = online;
    }

    private OnlineSummary(Builder builder) {
        this.devId = builder.devId;
        this.siteId = builder.siteId;
        this.online = builder.online;
    }

    public static Builder newBuilder(OnlineEvent event){
        Builder builder=new Builder();
        builder.online=event.getStatus().equals(new Integer(1));
        return builder;
    }

    public static  final class Builder{
        private Long devId;
        private Long siteId;
        private Boolean online;
        public static Builder newBuilder(){
            return new Builder();
        }
        public Builder withDevId(Long devId){
            this.devId=devId;
            return this;
        }
        public Builder withSiteId(Long siteId){
            this.siteId=siteId;
            return this;
        }
        public Builder withOnline(Boolean online){
            this.online=online;
            return this;
        }
        public OnlineSummary build(){
            return new OnlineSummary(this);
        }
    }

    @Override
    public String toString() {
        return "OnlineSummary{" +
                "devId=" + devId +
                ", siteId=" + siteId +
                ", online=" + online +
                '}';
    }

    public static void main(String[] args) {
        OnlineEvent event=new OnlineEvent("id",1);
        OnlineSummary summary=OnlineSummary.newBuilder(event).withDevId(123L).withSiteId(456L).build();
        System.out.println(summary);
    }
}

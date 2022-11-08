package ricotunes.services.card.dto;


import lombok.Data;

@Data
public class GiftCardDto {

    private String name;
    private String type;
    private String category;
    private double rmbRate;
    private double cardRate;
    private double profit;
    private double rate;

}
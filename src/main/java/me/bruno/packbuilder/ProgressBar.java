package me.bruno.packbuilder;

import java.util.Objects;

public class ProgressBar {


    private int remain = 0;
    private int total = 100;
    private int length = 10;
    private String[] filling = new String[]{"\u258F","\u258E", "\u258D", "\u258C", "\u258B", "\u258A", "\u2589", "\u2588"};
    private String startBracket = "[";
    private String endBracket = "]";
    private SystemColors barColor = SystemColors.GREEN_BOLD_BRIGHT;
    private SystemColors bracketColor = SystemColors.WHITE_BOLD_BRIGHT;
    private SystemColors percentageColor = SystemColors.CYAN_BOLD_BRIGHT;
    private boolean showBar = true;
    private boolean showPercentage = true;


    public ProgressBar(int remain, int total, int length, String[] filling, String startBracket, String endBracket, SystemColors barColor, SystemColors bracketColor, SystemColors percentageColor) {
        this.remain = remain;
        this.total = total;
        this.length = length;
        this.filling = filling;
        this.startBracket = startBracket;
        this.endBracket = endBracket;
        this.barColor = barColor;
        this.bracketColor = bracketColor;
        this.percentageColor = percentageColor;
    }
    public ProgressBar() {}


    public void setRemainingAmount(int remain) {
        this.remain = remain;
    }

    public void setMaxAmount(int total) {
        if ((total % 10) != 0) {
            throw new IllegalArgumentException("Number needs to be in factor of 10");
        } else {
            this.total = total;
        }
    }

    public void setBarLength(int length) {
        this.length = length;
    }
    public void setFillingValues(String[] filling) {
        this.filling = filling;
    }
    public void setStartBracket(String startBracket) {
        this.startBracket = startBracket;
    }

    public void setEndBracket(String endBracket) {
        this.endBracket = endBracket;
    }

    public void setBarColor(SystemColors barColor) {
        this.barColor = barColor;
    }

    public void setBracketColor(SystemColors bracketColor) {
        this.bracketColor = bracketColor;
    }

    public void setPercentageColor(SystemColors percentageColor) {
        this.percentageColor = percentageColor;
    }

    public void showPercentage(boolean value) {
        this.showPercentage = value;
    }

    public void showBar(boolean value) {
        this.showBar = value;
    }


    public int getRemainingAmount() {
        return remain;
    }

    public int getMaxAmount() {
        return total;
    }

    public int getBarLength() {
       return length;
    }
    public String[] getFillingValues() {
        return filling;
    }
    public String getStartBracket() {
        return startBracket;
    }

    public String getEndBracket() {
        return endBracket;
    }

    public SystemColors getBarColor() {
        return barColor;
    }

    public SystemColors getBracketColor() {
        return bracketColor;
    }

    public SystemColors getPercentageColor() {
        return percentageColor;
    }

    public boolean isBarShown() {
        return showBar;
    }

    public boolean isPercentageShown() {
        return showPercentage;
    }




    public String createBar() {

        if (remain > total) {
            throw new IllegalArgumentException();
        }
        double differentLength = total/(double)length;
        double toPercentage = (double) remain /((double) total /100);
        double eachModule = (total/(double)filling.length)/length;
        int modulesPassed = round(remain/differentLength);
        int moduleProgress = round((remain % differentLength)/eachModule);
        String builder = "";
        if (showBar) {
            builder +=startBracket;
            builder += filling[filling.length-1].repeat(Math.max(0, modulesPassed));
            if (!(moduleProgress <=0)) {
                builder += filling[moduleProgress-1];
            }
            builder+=" ".repeat(Math.max(0, length - (builder.length() - 1)));
            builder+= (endBracket + " ");
            if (bracketColor != null && barColor != null) {
                builder = builder.replace(startBracket,bracketColor + startBracket + barColor)
                        .replace(endBracket, bracketColor + endBracket);
            } else if (barColor != null || bracketColor != null) {
                if (barColor != null) {
                    builder = builder.replace(startBracket,startBracket + barColor)
                            .replace(endBracket, SystemColors.RESET + endBracket);
                } else {
                    builder = builder.replace(startBracket,bracketColor + startBracket + SystemColors.RESET)
                            .replace(endBracket, bracketColor + endBracket);
                }
            }

        }
        if (showPercentage) {
            builder += Objects.requireNonNullElse(percentageColor, SystemColors.RESET).toString() + toPercentage + "%" + SystemColors.RESET;
        } else {
            builder += SystemColors.RESET;
        }
        return builder;
    }
    private int round(double number) {
        double difference = Math.round(number) - number;
        if (difference <= 0.00000000001) {
            return (int) Math.round(number);
        } else {
            return (int) Math.floor(number);
        }
    }
}

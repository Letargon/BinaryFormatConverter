package com.iu3.binaryformatconverter.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BinaryView {

    private String hex;
    private String bin;
    private String dec;
    private Format type;

    public static enum Format {
        HEX, BIN, DEC;

        private Format() {
        }
    }

    public BinaryView(String num, Format type) {
        this.type = type;
        this.bin = "";
        this.dec = "";
        this.hex = "";
        switch (type) {
            case HEX:
                if (num.matches("[0-9A-F]+")) {
                    this.hex = num;
                } else {
                    throw new IllegalArgumentException("Illegal HEX Format");
                }
                break;
            case BIN:
                if (num.matches("[01]+")) {
                    this.bin = num;
                } else {
                    throw new IllegalArgumentException("Illegal BIN Format");
                }
                break;
            case DEC:
                if (num.matches("[0-9]+")) {
                    this.dec = num;
                } else {
                    throw new IllegalArgumentException("Illegal DEC Format");
                }
                break;
        }
    }

    public String getHex() {
        if (this.hex.isEmpty()) {
            switch (this.type) {
                case HEX:
                    return this.hex;
                case BIN:
                    this.hex = binToHex(this.bin);
                    break;
                case DEC:
                    this.hex = decToHex(this.dec);
            }
        }
        return this.hex;
    }

    public String getBin() {
        if (this.bin.isEmpty()) {
            switch (this.type) {
                case HEX:
                    this.bin = hexToBin(this.hex);
                case BIN:
                    return this.bin;
                case DEC:
                    this.bin = decToBin(this.dec);
            }
        }
        return this.bin;
    }

    public String getDec() {
        if (this.dec.isEmpty()) {
            switch (this.type) {
                case HEX:
                    this.dec = hexToDec(this.hex);
                case BIN:
                    this.dec = binToDec(this.bin);
                    break;
                case DEC:
                    return this.dec;
            }
        }
        return this.dec;
    }

    public static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

    public static String hexToDec(String s) {
        return new BigInteger(s, 16).toString(10);
    }

    public static String binToHex(String s) {
        return new BigInteger(s, 2).toString(16);
    }

    public static String binToDec(String s) {
        return new BigInteger(s, 2).toString(10);
    }

    public static String decToHex(String s) {
        return new BigInteger(s, 10).toString(16);
    }

    public static String decToBin(String s) {
        return new BigInteger(s, 10).toString(2);
    }

    public static String getDecimalN(String bin) {
        BigInteger res = BigInteger.ZERO;

        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                res = res.add(BigInteger.valueOf(2L).pow(bin.length() - 1 - i));
            }
        }
        return res.toString();
    }

    public String getDecimalN() {
        getBin();
        return getDecimalN(this.bin);
    }

    public static String getDecimalZ(String bin) {
        BigInteger res = new BigInteger(getDecimalN(bin.substring(1)));
        if (bin.charAt(0) == '1') {
            res = res.subtract(BigInteger.valueOf(2L).pow(bin.length() - 1));
        }

        return res.toString();
    }

    public String getDecimalZ() {
        if (this.type == Format.DEC) {
            return this.dec;
        }
        getBin();
        return getDecimalZ(this.bin);
    }

    public String getDecimalQ(String bin, int z) {
        String znum = getDecimalZ(bin);

        return new BigDecimal(znum).divide(new BigDecimal(
                BigInteger.valueOf(2L).pow(bin.length() - z))).toString();
    }

    public String getDecimalQ(int z) {
        if (this.type == Format.DEC) {
            return this.dec;
        }
        getBin();
        return getDecimalQ(this.bin, z);
    }
}

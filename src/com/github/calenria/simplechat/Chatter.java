package com.github.calenria.simplechat;

public class Chatter {
    private String  name;
    private String  conversionPartner = null;
    private String  server;

    private String  lastWhisperFrom;

    private boolean conversion        = false;
    private boolean lastWhisper       = false;

    /**
     * @return the conversion
     */
    public boolean isConversion() {
        return conversion;
    }

    public Chatter(String name, String server) {
        this.name = name;
        this.server = server;
    }

    /**
     * @return the conversionPartner
     */
    public String getLastWhisperFrom() {
        return lastWhisperFrom;
    }

    /**
     * @param conversionPartner
     *            the conversionPartner to set
     */
    public void setLastWhisperFrom(String from) {
        this.lastWhisperFrom = from;
        this.lastWhisper = true;
    }

    /**
     * @return the conversionPartner
     */
    public String getConversionPartner() {
        return conversionPartner;
    }

    /**
     * @param conversionPartner
     *            the conversionPartner to set
     */
    public void setConversionPartner(String conversionPartner) {
        this.conversionPartner = conversionPartner;
        this.conversion = true;
    }

    public void removeConversionPartner() {
        this.conversionPartner = null;
        this.conversion = false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @return the lastWhisper
     */
    public boolean isLastWhisper() {
        return lastWhisper;
    }

}

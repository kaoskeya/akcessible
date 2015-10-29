package com.lostinkaos.akcessible;

import java.util.List;

/**
 * Created by keya on 29/10/15.
 */
public class Localise {
    String inputLanguage, targetLanguage;
    int domain, status;
    List<Translated> outArray;

    public List<Translated> getOutArray() {
        return outArray;
    }

    public void setOutArray(List<Translated> outArray) {
        this.outArray = outArray;
    }

    public String getInputLanguage() {
        return inputLanguage;
    }

    public void setInputLanguage(String inputLanguage) {
        this.inputLanguage = inputLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public class Translated {
        String inString, response;
        int hitStatus;

        public String getInString() {
            return inString;
        }

        public void setInString(String inString) {
            this.inString = inString;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public int getHitStatus() {
            return hitStatus;
        }

        public void setHitStatus(int hitStatus) {
            this.hitStatus = hitStatus;
        }
    }
}

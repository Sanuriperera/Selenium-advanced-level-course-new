package com.pragmatic.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class Button implements  IButton, WrapsElement {
    private final WebElement btnElement;

    public Button(WebElement btnElement) {
//       this.btnElement=btnElement;
        String tagName = btnElement.getTagName();

        if (null != tagName && "input".equals(tagName.toLowerCase()) &&
                "submit".equals(btnElement.getDomAttribute("type"))) {
            this.btnElement = btnElement;
        } else {
            throw new UnexpectedTagNameException("input", tagName);
        }
    }

    public void click() {
        btnElement.click();
    }

    public boolean isEnabled() {
        return btnElement.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public WebElement getWrappedElement() {
        return btnElement;
    }
}

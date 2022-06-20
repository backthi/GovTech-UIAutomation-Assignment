package pages;

import org.openqa.selenium.By;

public class CommonPage
{
    By next_Btn = By.xpath("//button[@id='next-btn']");
    By save_Btn = By.xpath("//button[@id='save-btn']");
    By draftSaved_Text = By.xpath("//div[contains(text(),'Draft Saved')]");
}

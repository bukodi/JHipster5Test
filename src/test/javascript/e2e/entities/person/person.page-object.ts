import { element, by, promise, ElementFinder } from 'protractor';

export class PersonComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-person div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PersonUpdatePage {
    pageTitle = element(by.id('-person-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    titleInput = element(by.id('field_title'));
    firstNameInput = element(by.id('field_firstName'));
    lastNameInput = element(by.id('field_lastName'));
    idCardInput = element(by.id('field_idCard'));
    zipCodeInput = element(by.id('field_zipCode'));
    cityInput = element(by.id('field_city'));
    addressInput = element(by.id('field_address'));
    emailInput = element(by.id('field_email'));
    phoneInput = element(by.id('field_phone'));
    additionalInfoInput = element(by.id('field_additionalInfo'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setTitleInput(title): promise.Promise<void> {
        return this.titleInput.sendKeys(title);
    }

    getTitleInput() {
        return this.titleInput.getAttribute('value');
    }

    setFirstNameInput(firstName): promise.Promise<void> {
        return this.firstNameInput.sendKeys(firstName);
    }

    getFirstNameInput() {
        return this.firstNameInput.getAttribute('value');
    }

    setLastNameInput(lastName): promise.Promise<void> {
        return this.lastNameInput.sendKeys(lastName);
    }

    getLastNameInput() {
        return this.lastNameInput.getAttribute('value');
    }

    setIdCardInput(idCard): promise.Promise<void> {
        return this.idCardInput.sendKeys(idCard);
    }

    getIdCardInput() {
        return this.idCardInput.getAttribute('value');
    }

    setZipCodeInput(zipCode): promise.Promise<void> {
        return this.zipCodeInput.sendKeys(zipCode);
    }

    getZipCodeInput() {
        return this.zipCodeInput.getAttribute('value');
    }

    setCityInput(city): promise.Promise<void> {
        return this.cityInput.sendKeys(city);
    }

    getCityInput() {
        return this.cityInput.getAttribute('value');
    }

    setAddressInput(address): promise.Promise<void> {
        return this.addressInput.sendKeys(address);
    }

    getAddressInput() {
        return this.addressInput.getAttribute('value');
    }

    setEmailInput(email): promise.Promise<void> {
        return this.emailInput.sendKeys(email);
    }

    getEmailInput() {
        return this.emailInput.getAttribute('value');
    }

    setPhoneInput(phone): promise.Promise<void> {
        return this.phoneInput.sendKeys(phone);
    }

    getPhoneInput() {
        return this.phoneInput.getAttribute('value');
    }

    setAdditionalInfoInput(additionalInfo): promise.Promise<void> {
        return this.additionalInfoInput.sendKeys(additionalInfo);
    }

    getAdditionalInfoInput() {
        return this.additionalInfoInput.getAttribute('value');
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

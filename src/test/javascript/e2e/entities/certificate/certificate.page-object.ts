import { element, by, promise, ElementFinder } from 'protractor';

export class CertificateComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-certificate div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CertificateUpdatePage {
    pageTitle = element(by.id('-certificate-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    subjectNameInput = element(by.id('field_subjectName'));
    serialNumberInput = element(by.id('field_serialNumber'));
    typeSelect = element(by.id('field_type'));
    certDataInput = element(by.id('file_certData'));
    privateKeyInput = element(by.id('file_privateKey'));
    caSelect = element(by.id('field_ca'));
    templateSelect = element(by.id('field_template'));
    identitySelect = element(by.id('field_identity'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setSubjectNameInput(subjectName): promise.Promise<void> {
        return this.subjectNameInput.sendKeys(subjectName);
    }

    getSubjectNameInput() {
        return this.subjectNameInput.getAttribute('value');
    }

    setSerialNumberInput(serialNumber): promise.Promise<void> {
        return this.serialNumberInput.sendKeys(serialNumber);
    }

    getSerialNumberInput() {
        return this.serialNumberInput.getAttribute('value');
    }

    setTypeSelect(type): promise.Promise<void> {
        return this.typeSelect.sendKeys(type);
    }

    getTypeSelect() {
        return this.typeSelect.element(by.css('option:checked')).getText();
    }

    typeSelectLastOption(): promise.Promise<void> {
        return this.typeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }
    setCertDataInput(certData): promise.Promise<void> {
        return this.certDataInput.sendKeys(certData);
    }

    getCertDataInput() {
        return this.certDataInput.getAttribute('value');
    }

    setPrivateKeyInput(privateKey): promise.Promise<void> {
        return this.privateKeyInput.sendKeys(privateKey);
    }

    getPrivateKeyInput() {
        return this.privateKeyInput.getAttribute('value');
    }

    caSelectLastOption(): promise.Promise<void> {
        return this.caSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    caSelectOption(option): promise.Promise<void> {
        return this.caSelect.sendKeys(option);
    }

    getCaSelect(): ElementFinder {
        return this.caSelect;
    }

    getCaSelectedOption() {
        return this.caSelect.element(by.css('option:checked')).getText();
    }

    templateSelectLastOption(): promise.Promise<void> {
        return this.templateSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    templateSelectOption(option): promise.Promise<void> {
        return this.templateSelect.sendKeys(option);
    }

    getTemplateSelect(): ElementFinder {
        return this.templateSelect;
    }

    getTemplateSelectedOption() {
        return this.templateSelect.element(by.css('option:checked')).getText();
    }

    identitySelectLastOption(): promise.Promise<void> {
        return this.identitySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    identitySelectOption(option): promise.Promise<void> {
        return this.identitySelect.sendKeys(option);
    }

    getIdentitySelect(): ElementFinder {
        return this.identitySelect;
    }

    getIdentitySelectedOption() {
        return this.identitySelect.element(by.css('option:checked')).getText();
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

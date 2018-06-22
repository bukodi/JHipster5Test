import { element, by, promise, ElementFinder } from 'protractor';

export class CertificateTemplateComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-certificate-template div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CertificateTemplateUpdatePage {
    pageTitle = element(by.id('-certificate-template-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    typeSelect = element(by.id('field_type'));
    keyGenerationSelect = element(by.id('field_keyGeneration'));
    keyArchivationSelect = element(by.id('field_keyArchivation'));
    caSelect = element(by.id('field_ca'));
    ca2Select = element(by.id('field_ca2'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
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
    setKeyGenerationSelect(keyGeneration): promise.Promise<void> {
        return this.keyGenerationSelect.sendKeys(keyGeneration);
    }

    getKeyGenerationSelect() {
        return this.keyGenerationSelect.element(by.css('option:checked')).getText();
    }

    keyGenerationSelectLastOption(): promise.Promise<void> {
        return this.keyGenerationSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }
    setKeyArchivationSelect(keyArchivation): promise.Promise<void> {
        return this.keyArchivationSelect.sendKeys(keyArchivation);
    }

    getKeyArchivationSelect() {
        return this.keyArchivationSelect.element(by.css('option:checked')).getText();
    }

    keyArchivationSelectLastOption(): promise.Promise<void> {
        return this.keyArchivationSelect
            .all(by.tagName('option'))
            .last()
            .click();
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

    ca2SelectLastOption(): promise.Promise<void> {
        return this.ca2Select
            .all(by.tagName('option'))
            .last()
            .click();
    }

    ca2SelectOption(option): promise.Promise<void> {
        return this.ca2Select.sendKeys(option);
    }

    getCa2Select(): ElementFinder {
        return this.ca2Select;
    }

    getCa2SelectedOption() {
        return this.ca2Select.element(by.css('option:checked')).getText();
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

import { element, by, promise, ElementFinder } from 'protractor';

export class LogicalCardComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-logical-card div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class LogicalCardUpdatePage {
    pageTitle = element(by.id('-logical-card-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    physicalCardSelect = element(by.id('field_physicalCard'));
    certificatesSelect = element(by.id('field_certificates'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    physicalCardSelectLastOption(): promise.Promise<void> {
        return this.physicalCardSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    physicalCardSelectOption(option): promise.Promise<void> {
        return this.physicalCardSelect.sendKeys(option);
    }

    getPhysicalCardSelect(): ElementFinder {
        return this.physicalCardSelect;
    }

    getPhysicalCardSelectedOption() {
        return this.physicalCardSelect.element(by.css('option:checked')).getText();
    }

    certificatesSelectLastOption(): promise.Promise<void> {
        return this.certificatesSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    certificatesSelectOption(option): promise.Promise<void> {
        return this.certificatesSelect.sendKeys(option);
    }

    getCertificatesSelect(): ElementFinder {
        return this.certificatesSelect;
    }

    getCertificatesSelectedOption() {
        return this.certificatesSelect.element(by.css('option:checked')).getText();
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

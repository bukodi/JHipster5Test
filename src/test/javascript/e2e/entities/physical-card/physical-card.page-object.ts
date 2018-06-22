import { element, by, promise, ElementFinder } from 'protractor';

export class PhysicalCardComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-physical-card div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PhysicalCardUpdatePage {
    pageTitle = element(by.id('-physical-card-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    visualIdInput = element(by.id('field_visualId'));
    ownerSelect = element(by.id('field_owner'));
    typeSelect = element(by.id('field_type'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setVisualIdInput(visualId): promise.Promise<void> {
        return this.visualIdInput.sendKeys(visualId);
    }

    getVisualIdInput() {
        return this.visualIdInput.getAttribute('value');
    }

    ownerSelectLastOption(): promise.Promise<void> {
        return this.ownerSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    ownerSelectOption(option): promise.Promise<void> {
        return this.ownerSelect.sendKeys(option);
    }

    getOwnerSelect(): ElementFinder {
        return this.ownerSelect;
    }

    getOwnerSelectedOption() {
        return this.ownerSelect.element(by.css('option:checked')).getText();
    }

    typeSelectLastOption(): promise.Promise<void> {
        return this.typeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    typeSelectOption(option): promise.Promise<void> {
        return this.typeSelect.sendKeys(option);
    }

    getTypeSelect(): ElementFinder {
        return this.typeSelect;
    }

    getTypeSelectedOption() {
        return this.typeSelect.element(by.css('option:checked')).getText();
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

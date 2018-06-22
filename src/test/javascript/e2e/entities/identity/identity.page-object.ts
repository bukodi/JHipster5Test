import { element, by, promise, ElementFinder } from 'protractor';

export class IdentityComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-identity div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class IdentityUpdatePage {
    pageTitle = element(by.id('-identity-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    externalNameInput = element(by.id('field_externalName'));
    profileSelect = element(by.id('field_profile'));
    sourceSystemSelect = element(by.id('field_sourceSystem'));
    realPersonSelect = element(by.id('field_realPerson'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setExternalNameInput(externalName): promise.Promise<void> {
        return this.externalNameInput.sendKeys(externalName);
    }

    getExternalNameInput() {
        return this.externalNameInput.getAttribute('value');
    }

    profileSelectLastOption(): promise.Promise<void> {
        return this.profileSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    profileSelectOption(option): promise.Promise<void> {
        return this.profileSelect.sendKeys(option);
    }

    getProfileSelect(): ElementFinder {
        return this.profileSelect;
    }

    getProfileSelectedOption() {
        return this.profileSelect.element(by.css('option:checked')).getText();
    }

    sourceSystemSelectLastOption(): promise.Promise<void> {
        return this.sourceSystemSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    sourceSystemSelectOption(option): promise.Promise<void> {
        return this.sourceSystemSelect.sendKeys(option);
    }

    getSourceSystemSelect(): ElementFinder {
        return this.sourceSystemSelect;
    }

    getSourceSystemSelectedOption() {
        return this.sourceSystemSelect.element(by.css('option:checked')).getText();
    }

    realPersonSelectLastOption(): promise.Promise<void> {
        return this.realPersonSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    realPersonSelectOption(option): promise.Promise<void> {
        return this.realPersonSelect.sendKeys(option);
    }

    getRealPersonSelect(): ElementFinder {
        return this.realPersonSelect;
    }

    getRealPersonSelectedOption() {
        return this.realPersonSelect.element(by.css('option:checked')).getText();
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

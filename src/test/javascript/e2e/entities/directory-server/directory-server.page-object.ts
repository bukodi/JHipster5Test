import { element, by, promise, ElementFinder } from 'protractor';

export class DirectoryServerComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-directory-server div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DirectoryServerUpdatePage {
    pageTitle = element(by.id('-directory-server-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    hostInput = element(by.id('field_host'));
    customizationSourceInput = element(by.id('field_customizationSource'));
    customizationClassInput = element(by.id('file_customizationClass'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setHostInput(host): promise.Promise<void> {
        return this.hostInput.sendKeys(host);
    }

    getHostInput() {
        return this.hostInput.getAttribute('value');
    }

    setCustomizationSourceInput(customizationSource): promise.Promise<void> {
        return this.customizationSourceInput.sendKeys(customizationSource);
    }

    getCustomizationSourceInput() {
        return this.customizationSourceInput.getAttribute('value');
    }

    setCustomizationClassInput(customizationClass): promise.Promise<void> {
        return this.customizationClassInput.sendKeys(customizationClass);
    }

    getCustomizationClassInput() {
        return this.customizationClassInput.getAttribute('value');
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

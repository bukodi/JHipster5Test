import { element, by, promise, ElementFinder } from 'protractor';

export class CustomProcessComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-custom-process div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class CustomProcessUpdatePage {
    pageTitle = element(by.id('-custom-process-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    interfaceFqnInput = element(by.id('field_interfaceFqn'));
    templateInput = element(by.id('field_template'));
    sourceInput = element(by.id('field_source'));
    schedulingInput = element(by.id('field_scheduling'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setInterfaceFqnInput(interfaceFqn): promise.Promise<void> {
        return this.interfaceFqnInput.sendKeys(interfaceFqn);
    }

    getInterfaceFqnInput() {
        return this.interfaceFqnInput.getAttribute('value');
    }

    getTemplateInput() {
        return this.templateInput;
    }
    setSourceInput(source): promise.Promise<void> {
        return this.sourceInput.sendKeys(source);
    }

    getSourceInput() {
        return this.sourceInput.getAttribute('value');
    }

    setSchedulingInput(scheduling): promise.Promise<void> {
        return this.schedulingInput.sendKeys(scheduling);
    }

    getSchedulingInput() {
        return this.schedulingInput.getAttribute('value');
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

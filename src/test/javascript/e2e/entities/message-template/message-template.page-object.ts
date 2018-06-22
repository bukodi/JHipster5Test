import { element, by, promise, ElementFinder } from 'protractor';

export class MessageTemplateComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-message-template div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class MessageTemplateUpdatePage {
    pageTitle = element(by.id('-message-template-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    instantInput = element(by.id('field_instant'));
    eventTypeFqnInput = element(by.id('field_eventTypeFqn'));
    templateInput = element(by.id('field_template'));
    sourceInput = element(by.id('field_source'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setInstantInput(instant): promise.Promise<void> {
        return this.instantInput.sendKeys(instant);
    }

    getInstantInput() {
        return this.instantInput.getAttribute('value');
    }

    setEventTypeFqnInput(eventTypeFqn): promise.Promise<void> {
        return this.eventTypeFqnInput.sendKeys(eventTypeFqn);
    }

    getEventTypeFqnInput() {
        return this.eventTypeFqnInput.getAttribute('value');
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

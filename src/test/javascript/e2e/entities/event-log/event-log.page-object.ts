import { element, by, promise, ElementFinder } from 'protractor';

export class EventLogComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('-event-log div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class EventLogUpdatePage {
    pageTitle = element(by.id('-event-log-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    instantInput = element(by.id('field_instant'));
    eventTypeFqnInput = element(by.id('field_eventTypeFqn'));
    nodeInput = element(by.id('field_node'));
    serachKeyInput = element(by.id('field_serachKey'));
    payloadInput = element(by.id('field_payload'));

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

    setNodeInput(node): promise.Promise<void> {
        return this.nodeInput.sendKeys(node);
    }

    getNodeInput() {
        return this.nodeInput.getAttribute('value');
    }

    setSerachKeyInput(serachKey): promise.Promise<void> {
        return this.serachKeyInput.sendKeys(serachKey);
    }

    getSerachKeyInput() {
        return this.serachKeyInput.getAttribute('value');
    }

    setPayloadInput(payload): promise.Promise<void> {
        return this.payloadInput.sendKeys(payload);
    }

    getPayloadInput() {
        return this.payloadInput.getAttribute('value');
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

import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { MessageTemplateComponentsPage, MessageTemplateUpdatePage } from './message-template.page-object';

describe('MessageTemplate e2e test', () => {
    let navBarPage: NavBarPage;
    let messageTemplateUpdatePage: MessageTemplateUpdatePage;
    let messageTemplateComponentsPage: MessageTemplateComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load MessageTemplates', () => {
        navBarPage.goToEntity('message-template');
        messageTemplateComponentsPage = new MessageTemplateComponentsPage();
        expect(messageTemplateComponentsPage.getTitle()).toMatch(/jHipster5TestApp.messageTemplate.home.title/);
    });

    it('should load create MessageTemplate page', () => {
        messageTemplateComponentsPage.clickOnCreateButton();
        messageTemplateUpdatePage = new MessageTemplateUpdatePage();
        expect(messageTemplateUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.messageTemplate.home.createOrEditLabel/);
        messageTemplateUpdatePage.cancel();
    });

    it('should create and save MessageTemplates', () => {
        messageTemplateComponentsPage.clickOnCreateButton();
        messageTemplateUpdatePage.setInstantInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(messageTemplateUpdatePage.getInstantInput()).toContain('2001-01-01T02:30');
        messageTemplateUpdatePage.setEventTypeFqnInput('eventTypeFqn');
        expect(messageTemplateUpdatePage.getEventTypeFqnInput()).toMatch('eventTypeFqn');
        messageTemplateUpdatePage
            .getTemplateInput()
            .isSelected()
            .then(selected => {
                if (selected) {
                    messageTemplateUpdatePage.getTemplateInput().click();
                    expect(messageTemplateUpdatePage.getTemplateInput().isSelected()).toBeFalsy();
                } else {
                    messageTemplateUpdatePage.getTemplateInput().click();
                    expect(messageTemplateUpdatePage.getTemplateInput().isSelected()).toBeTruthy();
                }
            });
        messageTemplateUpdatePage.setSourceInput('source');
        expect(messageTemplateUpdatePage.getSourceInput()).toMatch('source');
        messageTemplateUpdatePage.save();
        expect(messageTemplateUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

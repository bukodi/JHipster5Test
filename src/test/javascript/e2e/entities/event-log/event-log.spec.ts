import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { EventLogComponentsPage, EventLogUpdatePage } from './event-log.page-object';

describe('EventLog e2e test', () => {
    let navBarPage: NavBarPage;
    let eventLogUpdatePage: EventLogUpdatePage;
    let eventLogComponentsPage: EventLogComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load EventLogs', () => {
        navBarPage.goToEntity('event-log');
        eventLogComponentsPage = new EventLogComponentsPage();
        expect(eventLogComponentsPage.getTitle()).toMatch(/jHipster5TestApp.eventLog.home.title/);
    });

    it('should load create EventLog page', () => {
        eventLogComponentsPage.clickOnCreateButton();
        eventLogUpdatePage = new EventLogUpdatePage();
        expect(eventLogUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.eventLog.home.createOrEditLabel/);
        eventLogUpdatePage.cancel();
    });

    it('should create and save EventLogs', () => {
        eventLogComponentsPage.clickOnCreateButton();
        eventLogUpdatePage.setInstantInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(eventLogUpdatePage.getInstantInput()).toContain('2001-01-01T02:30');
        eventLogUpdatePage.setEventTypeFqnInput('eventTypeFqn');
        expect(eventLogUpdatePage.getEventTypeFqnInput()).toMatch('eventTypeFqn');
        eventLogUpdatePage.setNodeInput('node');
        expect(eventLogUpdatePage.getNodeInput()).toMatch('node');
        eventLogUpdatePage.setSerachKeyInput('serachKey');
        expect(eventLogUpdatePage.getSerachKeyInput()).toMatch('serachKey');
        eventLogUpdatePage.setPayloadInput('payload');
        expect(eventLogUpdatePage.getPayloadInput()).toMatch('payload');
        eventLogUpdatePage.save();
        expect(eventLogUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

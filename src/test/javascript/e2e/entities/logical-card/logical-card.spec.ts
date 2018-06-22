import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { LogicalCardComponentsPage, LogicalCardUpdatePage } from './logical-card.page-object';

describe('LogicalCard e2e test', () => {
    let navBarPage: NavBarPage;
    let logicalCardUpdatePage: LogicalCardUpdatePage;
    let logicalCardComponentsPage: LogicalCardComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load LogicalCards', () => {
        navBarPage.goToEntity('logical-card');
        logicalCardComponentsPage = new LogicalCardComponentsPage();
        expect(logicalCardComponentsPage.getTitle()).toMatch(/jHipster5TestApp.logicalCard.home.title/);
    });

    it('should load create LogicalCard page', () => {
        logicalCardComponentsPage.clickOnCreateButton();
        logicalCardUpdatePage = new LogicalCardUpdatePage();
        expect(logicalCardUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.logicalCard.home.createOrEditLabel/);
        logicalCardUpdatePage.cancel();
    });

    /* it('should create and save LogicalCards', () => {
        logicalCardComponentsPage.clickOnCreateButton();
        logicalCardUpdatePage.setNameInput('name');
        expect(logicalCardUpdatePage.getNameInput()).toMatch('name');
        logicalCardUpdatePage.physicalCardSelectLastOption();
        // logicalCardUpdatePage.certificatesSelectLastOption();
        logicalCardUpdatePage.save();
        expect(logicalCardUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PhysicalCardComponentsPage, PhysicalCardUpdatePage } from './physical-card.page-object';

describe('PhysicalCard e2e test', () => {
    let navBarPage: NavBarPage;
    let physicalCardUpdatePage: PhysicalCardUpdatePage;
    let physicalCardComponentsPage: PhysicalCardComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PhysicalCards', () => {
        navBarPage.goToEntity('physical-card');
        physicalCardComponentsPage = new PhysicalCardComponentsPage();
        expect(physicalCardComponentsPage.getTitle()).toMatch(/jHipster5TestApp.physicalCard.home.title/);
    });

    it('should load create PhysicalCard page', () => {
        physicalCardComponentsPage.clickOnCreateButton();
        physicalCardUpdatePage = new PhysicalCardUpdatePage();
        expect(physicalCardUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.physicalCard.home.createOrEditLabel/);
        physicalCardUpdatePage.cancel();
    });

    /* it('should create and save PhysicalCards', () => {
        physicalCardComponentsPage.clickOnCreateButton();
        physicalCardUpdatePage.setVisualIdInput('visualId');
        expect(physicalCardUpdatePage.getVisualIdInput()).toMatch('visualId');
        physicalCardUpdatePage.ownerSelectLastOption();
        physicalCardUpdatePage.typeSelectLastOption();
        physicalCardUpdatePage.save();
        expect(physicalCardUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { IdentityComponentsPage, IdentityUpdatePage } from './identity.page-object';

describe('Identity e2e test', () => {
    let navBarPage: NavBarPage;
    let identityUpdatePage: IdentityUpdatePage;
    let identityComponentsPage: IdentityComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Identities', () => {
        navBarPage.goToEntity('identity');
        identityComponentsPage = new IdentityComponentsPage();
        expect(identityComponentsPage.getTitle()).toMatch(/jHipster5TestApp.identity.home.title/);
    });

    it('should load create Identity page', () => {
        identityComponentsPage.clickOnCreateButton();
        identityUpdatePage = new IdentityUpdatePage();
        expect(identityUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.identity.home.createOrEditLabel/);
        identityUpdatePage.cancel();
    });

    it('should create and save Identities', () => {
        identityComponentsPage.clickOnCreateButton();
        identityUpdatePage.setNameInput('name');
        expect(identityUpdatePage.getNameInput()).toMatch('name');
        identityUpdatePage.setExternalNameInput('externalName');
        expect(identityUpdatePage.getExternalNameInput()).toMatch('externalName');
        identityUpdatePage.profileSelectLastOption();
        identityUpdatePage.sourceSystemSelectLastOption();
        identityUpdatePage.realPersonSelectLastOption();
        identityUpdatePage.save();
        expect(identityUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

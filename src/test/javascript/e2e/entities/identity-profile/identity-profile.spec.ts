import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { IdentityProfileComponentsPage, IdentityProfileUpdatePage } from './identity-profile.page-object';

describe('IdentityProfile e2e test', () => {
    let navBarPage: NavBarPage;
    let identityProfileUpdatePage: IdentityProfileUpdatePage;
    let identityProfileComponentsPage: IdentityProfileComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load IdentityProfiles', () => {
        navBarPage.goToEntity('identity-profile');
        identityProfileComponentsPage = new IdentityProfileComponentsPage();
        expect(identityProfileComponentsPage.getTitle()).toMatch(/jHipster5TestApp.identityProfile.home.title/);
    });

    it('should load create IdentityProfile page', () => {
        identityProfileComponentsPage.clickOnCreateButton();
        identityProfileUpdatePage = new IdentityProfileUpdatePage();
        expect(identityProfileUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.identityProfile.home.createOrEditLabel/);
        identityProfileUpdatePage.cancel();
    });

    it('should create and save IdentityProfiles', () => {
        identityProfileComponentsPage.clickOnCreateButton();
        identityProfileUpdatePage.setNameInput('name');
        expect(identityProfileUpdatePage.getNameInput()).toMatch('name');
        identityProfileUpdatePage.save();
        expect(identityProfileUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

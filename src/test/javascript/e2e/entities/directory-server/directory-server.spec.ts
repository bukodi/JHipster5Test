import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { DirectoryServerComponentsPage, DirectoryServerUpdatePage } from './directory-server.page-object';
import * as path from 'path';

describe('DirectoryServer e2e test', () => {
    let navBarPage: NavBarPage;
    let directoryServerUpdatePage: DirectoryServerUpdatePage;
    let directoryServerComponentsPage: DirectoryServerComponentsPage;
    const fileToUpload = '../../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load DirectoryServers', () => {
        navBarPage.goToEntity('directory-server');
        directoryServerComponentsPage = new DirectoryServerComponentsPage();
        expect(directoryServerComponentsPage.getTitle()).toMatch(/jHipster5TestApp.directoryServer.home.title/);
    });

    it('should load create DirectoryServer page', () => {
        directoryServerComponentsPage.clickOnCreateButton();
        directoryServerUpdatePage = new DirectoryServerUpdatePage();
        expect(directoryServerUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.directoryServer.home.createOrEditLabel/);
        directoryServerUpdatePage.cancel();
    });

    it('should create and save DirectoryServers', () => {
        directoryServerComponentsPage.clickOnCreateButton();
        directoryServerUpdatePage.setNameInput('name');
        expect(directoryServerUpdatePage.getNameInput()).toMatch('name');
        directoryServerUpdatePage.setHostInput('host');
        expect(directoryServerUpdatePage.getHostInput()).toMatch('host');
        directoryServerUpdatePage.setCustomizationSourceInput('customizationSource');
        expect(directoryServerUpdatePage.getCustomizationSourceInput()).toMatch('customizationSource');
        directoryServerUpdatePage.setCustomizationClassInput(absolutePath);
        directoryServerUpdatePage.save();
        expect(directoryServerUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

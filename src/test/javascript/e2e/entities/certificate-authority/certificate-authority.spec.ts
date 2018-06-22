import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CertificateAuthorityComponentsPage, CertificateAuthorityUpdatePage } from './certificate-authority.page-object';
import * as path from 'path';

describe('CertificateAuthority e2e test', () => {
    let navBarPage: NavBarPage;
    let certificateAuthorityUpdatePage: CertificateAuthorityUpdatePage;
    let certificateAuthorityComponentsPage: CertificateAuthorityComponentsPage;
    const fileToUpload = '../../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load CertificateAuthorities', () => {
        navBarPage.goToEntity('certificate-authority');
        certificateAuthorityComponentsPage = new CertificateAuthorityComponentsPage();
        expect(certificateAuthorityComponentsPage.getTitle()).toMatch(/jHipster5TestApp.certificateAuthority.home.title/);
    });

    it('should load create CertificateAuthority page', () => {
        certificateAuthorityComponentsPage.clickOnCreateButton();
        certificateAuthorityUpdatePage = new CertificateAuthorityUpdatePage();
        expect(certificateAuthorityUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.certificateAuthority.home.createOrEditLabel/);
        certificateAuthorityUpdatePage.cancel();
    });

    it('should create and save CertificateAuthorities', () => {
        certificateAuthorityComponentsPage.clickOnCreateButton();
        certificateAuthorityUpdatePage.setNameInput('name');
        expect(certificateAuthorityUpdatePage.getNameInput()).toMatch('name');
        certificateAuthorityUpdatePage.setHostInput('host');
        expect(certificateAuthorityUpdatePage.getHostInput()).toMatch('host');
        certificateAuthorityUpdatePage.setCustomizationSourceInput('customizationSource');
        expect(certificateAuthorityUpdatePage.getCustomizationSourceInput()).toMatch('customizationSource');
        certificateAuthorityUpdatePage.setCustomizationClassInput(absolutePath);
        certificateAuthorityUpdatePage.save();
        expect(certificateAuthorityUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

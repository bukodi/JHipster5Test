import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CertificateComponentsPage, CertificateUpdatePage } from './certificate.page-object';
import * as path from 'path';

describe('Certificate e2e test', () => {
    let navBarPage: NavBarPage;
    let certificateUpdatePage: CertificateUpdatePage;
    let certificateComponentsPage: CertificateComponentsPage;
    const fileToUpload = '../../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Certificates', () => {
        navBarPage.goToEntity('certificate');
        certificateComponentsPage = new CertificateComponentsPage();
        expect(certificateComponentsPage.getTitle()).toMatch(/jHipster5TestApp.certificate.home.title/);
    });

    it('should load create Certificate page', () => {
        certificateComponentsPage.clickOnCreateButton();
        certificateUpdatePage = new CertificateUpdatePage();
        expect(certificateUpdatePage.getPageTitle()).toMatch(/jHipster5TestApp.certificate.home.createOrEditLabel/);
        certificateUpdatePage.cancel();
    });

    /* it('should create and save Certificates', () => {
        certificateComponentsPage.clickOnCreateButton();
        certificateUpdatePage.setSubjectNameInput('subjectName');
        expect(certificateUpdatePage.getSubjectNameInput()).toMatch('subjectName');
        certificateUpdatePage.setSerialNumberInput('serialNumber');
        expect(certificateUpdatePage.getSerialNumberInput()).toMatch('serialNumber');
        certificateUpdatePage.typeSelectLastOption();
        certificateUpdatePage.setCertDataInput(absolutePath);
        certificateUpdatePage.setPrivateKeyInput(absolutePath);
        certificateUpdatePage.caSelectLastOption();
        certificateUpdatePage.templateSelectLastOption();
        certificateUpdatePage.identitySelectLastOption();
        certificateUpdatePage.save();
        expect(certificateUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });*/

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

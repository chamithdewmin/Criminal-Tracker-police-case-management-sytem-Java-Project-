package com.example.crimejava.Controllers;

import com.example.crimejava.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.crimejava.Utils.ShowAlert.showAlert;

public class CaseAddController implements Initializable {

    public TextField locationTxt;
    public TextField filingNum;
    public TextField criminalName;
    public TextField officerNametxt;
    public TextField criminalNic;
    public TextArea discriptiontxt;
    public ComboBox<String> stageofCase;
    public ComboBox<String> caseType;
    public ComboBox<String> subType;
    public ComboBox<String> caseStautsCombo;
    public Button cancelBtn;
    public Button backBtn;
    public Button saveBtn;
    public DatePicker cirmeDate;
    public DatePicker filingDate;
    public DatePicker fhearingDate;
    public ComboBox<String> prioritybox;
    public TextField OfficerBadgeNumber;
    public ComboBox policeStationcombo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backBtn.setOnAction(actionEvent -> onBack());
        saveBtn.setOnAction(actionEvent -> onSave());
        cancelBtn.setOnAction(actionEvent -> onCancel());

        stageofCase.getItems().addAll(
                "1. Initial Report",
                "   Complaint: A report of a crime is made by a victim, witness, or law enforcement officer.",
                "   Investigation Initiation: Law enforcement begins gathering evidence and information related to the reported crime.",
                // Stage 2: Investigation
                "2. Investigation",
                "   Evidence Collection: Gathering physical evidence, interviewing witnesses, and collecting statements.",
                "   Suspect Identification: Identifying and locating potential suspects.",
                "   Forensic Analysis: Analyzing evidence such as fingerprints, DNA, or digital data.",
                "   Surveillance: Monitoring suspects or locations related to the case.",
                // Stage 3: Arrest and Booking
                "3. Arrest and Booking",
                "   Arrest: Law enforcement apprehends the suspect(s) based on evidence and legal authority.",
                "   Booking: The suspect is processed, which includes recording personal information, taking fingerprints, and photographing.",
                // Stage 4: Charging
                "4. Charging",
                "   Charge Filing: The prosecutor reviews evidence and decides whether to file charges.",
                "   Formal Charges: Charges are formally brought against the suspect, specifying the crimes they are accused of.",
                // Stage 5: Pre-Trial Proceedings
                "5. Pre-Trial Proceedings",
                "   Arraignment: The suspect appears in court to enter a plea (guilty, not guilty, or no contest).",
                "   Bail Hearing: The court decides whether the suspect will be released on bail and under what conditions.",
                "   Pre-Trial Motions: Legal motions are filed, including requests for evidence, dismissals, or changes of venue.",
                "   Discovery: Both the prosecution and defense exchange evidence and information relevant to the case.",
                // Stage 6: Trial Preparation
                "6. Trial Preparation",
                "   Witness Preparation: Preparing witnesses to testify in court.",
                "   Trial Strategy: The defense and prosecution develop their strategies and arguments for trial.",
                "   Jury Selection: Selecting a jury from a pool of potential jurors, if applicable.",
                // Stage 7: Trial
                "7. Trial",
                "   Opening Statements: Both the prosecution and defense present their initial arguments.",
                "   Presentation of Evidence: Both sides present evidence, including witness testimony and physical evidence.",
                "   Cross-Examination: Witnesses are questioned by both sides.",
                "   Closing Arguments: Both sides summarize their cases and argue why the evidence supports their position.",
                "   Jury Deliberation: The jury (in a jury trial) discusses the case and reaches a verdict.",
                // Stage 8: Verdict
                "8. Verdict",
                "   Guilty: The jury or judge finds the defendant guilty of the charges.",
                "   Not Guilty: The jury or judge finds the defendant not guilty of the charges.",
                "   Hung Jury: The jury cannot reach a unanimous decision, potentially leading to a mistrial.",
                // Stage 9: Sentencing
                "9. Sentencing",
                "   Sentencing Hearing: A separate court proceeding where the judge determines the appropriate punishment for a guilty verdict.",
                "   Punishment: The defendant is sentenced according to the crime and legal guidelines (e.g., prison, probation, fines).",
                // Stage 10: Appeals
                "10. Appeals",
                "   Appeal Filing: The convicted defendant may appeal the conviction or sentence, arguing that legal errors occurred.",
                "   Appellate Review: An appellate court reviews the trial court’s decisions for errors and determines whether to uphold or overturn the conviction.",
                // Stage 11: Post-Conviction
                "11. Post-Conviction",
                "   Post-Conviction Relief: Requests for relief from the conviction based on new evidence or legal issues.",
                "   Rehabilitation: Programs or actions taken to support the convicted individual’s reintegration into society, if applicable.",
                // Stage 12: Case Closure
                "12. Case Closure",
                "   Completion: The case is formally closed once all legal avenues have been exhausted, and the sentence has been served or modified."
        );

        // Adding values to caseType ComboBox
        caseType.getItems().addAll(
                "Violent Crimes",
                "   Homicide",
                "   Assault",
                "   Robbery",
                "   Domestic Violence",
                "Property Crimes",
                "   Burglary",
                "   Theft/Larceny",
                "   Motor Vehicle Theft",
                "   Vandalism",
                "Cyber Crimes",
                "   Hacking",
                "   Identity Theft",
                "   Online Fraud",
                "   Cyberbullying",
                "Drug-Related Crimes",
                "   Drug Trafficking",
                "   Drug Possession",
                "   Drug Manufacturing",
                "   Drug Paraphernalia",
                "White-Collar Crimes",
                "   Fraud",
                "   Bribery",
                "   Money Laundering",
                "   Corporate Crime",
                "Sex Crimes",
                "   Rape",
                "   Sexual Harassment",
                "   Child Sexual Abuse",
                "   Human Trafficking",
                "Public Order Crimes",
                "   Disorderly Conduct",
                "   Public Intoxication",
                "   Loitering",
                "   Gambling Offenses",
                "Traffic Offenses",
                "   DUI/DWI",
                "   Reckless Driving",
                "   Hit and Run",
                "   Driving Without a License"
        );

        subType.getItems().addAll(
                "1. Violent Crimes",
                "   Homicide",
                "       First-Degree Murder: Premeditated and intentional killing.",
                "       Second-Degree Murder: Intentional killing without premeditation.",
                "       Manslaughter: Unintentional killing due to reckless behavior.",
                "       Infanticide: Killing of an infant, typically by the mother.",
                "   Assault",
                "       Simple Assault: Causing minor physical harm without a weapon.",
                "       Aggravated Assault: Causing serious injury, often with a weapon.",
                "       Sexual Assault: Non-consensual sexual contact.",
                "       Battery: Intentional infliction of harmful or offensive physical contact.",
                "   Robbery",
                "       Armed Robbery: Robbery involving the use of a weapon.",
                "       Bank Robbery: Robbing a bank or financial institution.",
                "       Carjacking: Robbing someone of their vehicle by force.",
                "   Domestic Violence",
                "       Spousal Abuse: Physical or emotional abuse within a marriage.",
                "       Child Abuse: Physical or emotional harm to a child.",
                "       Elder Abuse: Physical or emotional harm to an elderly person.",
                "2. Property Crimes",
                "   Burglary",
                "       Residential Burglary: Breaking into a home to commit a crime.",
                "       Commercial Burglary: Breaking into a business or commercial property.",
                "       Home Invasion: Forcible entry into an occupied home to commit robbery or assault.",
                "   Theft/Larceny",
                "       Petty Theft: Theft of items of low value.",
                "       Grand Theft: Theft of items of high value.",
                "       Shoplifting: Stealing items from a retail store.",
                "       Pickpocketing: Stealing from a person's pocket or bag without their notice.",
                "   Motor Vehicle Theft",
                "       Joyriding: Stealing a car for temporary use, often by juveniles.",
                "       Car Theft: Stealing a vehicle with intent to permanently deprive the owner.",
                "       Carjacking: Forcing someone to give up their car through violence or intimidation.",
                "   Vandalism",
                "       Graffiti: Defacing property with spray paint or markers.",
                "       Arson: Intentional setting of fires to property.",
                "       Destruction of Property: Breaking windows, slashing tires, etc.",
                "3. Cyber Crimes",
                "   Hacking",
                "       Phishing: Fraudulently obtaining sensitive information by pretending to be a trustworthy entity.",
                "       Ransomware: Encrypting someone's data and demanding payment for its release.",
                "       DDoS Attacks: Overloading a system with traffic to make it unavailable.",
                "   Identity Theft",
                "       Financial Identity Theft: Stealing someone's identity to commit financial fraud.",
                "       Medical Identity Theft: Using someone else's identity to obtain medical services.",
                "       Criminal Identity Theft: Giving someone else's name or identity when arrested.",
                "   Online Fraud",
                "       Credit Card Fraud: Using someone else's credit card information illegally.",
                "       Auction Fraud: Misrepresenting a product in online auctions.",
                "       Investment Fraud: Offering fake or misleading investment opportunities.",
                "   Cyberbullying",
                "       Harassment: Sending threatening or abusive messages online.",
                "       Trolling: Posting inflammatory or off-topic messages to provoke others.",
                "       Stalking: Using electronic means to repeatedly harass or threaten someone.",
                "4. Drug-Related Crimes",
                "   Drug Trafficking",
                "       International Drug Trafficking: Smuggling drugs across international borders.",
                "       Domestic Drug Distribution: Selling drugs within a country.",
                "       Street-Level Drug Dealing: Selling drugs directly to users.",
                "   Drug Possession",
                "       Simple Possession: Possession of a small amount for personal use.",
                "       Possession with Intent to Distribute: Possession of a large quantity indicating intent to sell.",
                "       Paraphernalia Possession: Possessing items used to consume or distribute drugs.",
                "   Manufacturing of Drugs",
                "       Methamphetamine Production: Illegal production of methamphetamine.",
                "       Marijuana Cultivation: Growing marijuana illegally.",
                "       Synthetic Drug Manufacturing: Producing synthetic drugs like ecstasy or LSD.",
                "   Drug Paraphernalia",
                "       Bongs/Pipes: Devices used to consume drugs.",
                "       Needles/Syringes: Items used for injecting drugs.",
                "       Scales/Packaging: Items used for weighing and packaging drugs for sale.",
                "5. White-Collar Crimes",
                "   Fraud",
                "       Insurance Fraud: Filing false insurance claims.",
                "       Securities Fraud: Manipulating financial markets or insider trading.",
                "       Mortgage Fraud: Providing false information to obtain a mortgage.",
                "   Bribery",
                "       Public Official Bribery: Offering money or favors to influence a public official.",
                "       Corporate Bribery: Offering bribes to secure business contracts.",
                "       Judicial Bribery: Attempting to influence judges or jurors.",
                "   Money Laundering",
                "       Smurfing: Breaking down large sums of money into smaller transactions to avoid detection.",
                "       Shell Companies: Using fake companies to disguise illegal funds.",
                "       Offshore Accounts: Hiding money in foreign banks to avoid detection.",
                "   Corporate Crime",
                "       Embezzlement: Stealing funds from a company by someone in a trusted position.",
                "       Accounting Fraud: Manipulating financial statements to deceive stakeholders.",
                "       Antitrust Violations: Engaging in practices that restrict competition.",
                "6. Sex Crimes",
                "   Rape",
                "       Date Rape: Rape by someone the victim knows, often in a social setting.",
                "       Statutory Rape: Sexual activity with someone below the age of consent.",
                "       Marital Rape: Non-consensual sex within a marriage.",
                "   Sexual Harassment",
                "       Workplace Harassment: Unwanted sexual advances or behavior in a work setting.",
                "       Street Harassment: Unwanted comments, gestures, or actions in public.",
                "       Online Harassment: Sending sexually explicit messages or images online.",
                "   Child Sexual Abuse",
                "       Child Pornography: Production, distribution, or possession of sexually explicit material involving minors.",
                "       Grooming: Building a relationship with a child to exploit them sexually.",
                "       Incest: Sexual activity between close family members.",
                "   Human Trafficking",
                "       Sex Trafficking: Forcing or coercing someone into commercial sex acts.",
                "       Labor Trafficking: Forcing someone into labor or services.",
                "       Child Trafficking: Exploiting minors for labor, sex, or other purposes.",
                "7. Public Order Crimes",
                "   Disorderly Conduct",
                "       Public Fighting: Engaging in physical altercations in public places.",
                "       Disturbing the Peace: Making excessive noise or causing a public disturbance.",
                "       Obstructing Traffic: Blocking roads or public pathways.",
                "   Public Intoxication",
                "       Drunken Disorderly: Causing a disturbance while intoxicated.",
                "       Alcohol Consumption in Public: Drinking alcohol in areas where it’s prohibited.",
                "       Drug-Induced Behavior: Erratic or dangerous behavior due to drug use.",
                "   Loitering",
                "       Loitering with Intent: Staying in a public place with the intent to commit a crime.",
                "       Vagrancy: Remaining in public without visible means of support.",
                "       Solicitation: Offering or engaging in illegal services in public places.",
                "   Gambling Offenses",
                "       Illegal Betting: Engaging in unauthorized betting activities.",
                "       Operating an Illegal Casino: Running a gambling operation without proper licensing.",
                "       Lottery Fraud: Selling or promoting fake lottery schemes.",
                "8. Traffic Offenses",
                "   DUI/DWI",
                "       Alcohol-Related DUI: Driving under the influence of alcohol.",
                "       Drug-Related DUI: Driving under the influence of illegal or prescription drugs.",
                "       Refusal to Submit to Testing: Refusing to take a breathalyzer or other sobriety tests.",
                "   Reckless Driving",
                "       Street Racing: Engaging in unauthorized and dangerous speed competitions.",
                "       Aggressive Driving: Dangerous driving behavior, such as tailgating or weaving.",
                "       Excessive Speeding: Driving at speeds far above the legal limit.",
                "   Hit and Run",
                "       Property Damage Hit and Run: Leaving the scene after damaging property.",
                "       Personal Injury Hit and Run: Leaving the scene after injuring someone.",
                "       Fatal Hit and Run: Leaving the scene after causing a fatality.",
                "   Driving Without a License",
                "       Suspended License: Driving with a license that has been revoked or suspended.",
                "       Unlicensed Driver: Operating a vehicle without ever having been issued a license."
        );

        policeStationcombo.getItems().addAll(
                "Western Province",
                "- Colombo",
                "    Colombo Fort Police Station",
                "    Colombo North Police Station",
                "    Colombo South Police Station",
                "    Colombo East Police Station",
                "- Gampaha",
                "    Gampaha Police Station",
                "    Negombo Police Station",
                "    Kadawatha Police Station",
                "    Kalutara",
                "    Kalutara Police Station",
                "    Panadura Police Station",
                "    Central Province",
                "- Kandy",
                "    Kandy Police Station",
                "    Peradeniya Police Station",
                "- Matale",
                "    Matale Police Station",
                "- Nuwara Eliya",
                "    Nuwara Eliya Police Station",
                "Southern Province",
                "- Galle",
                "    Galle Police Station",
                "    Hikkaduwa Police Station",
                "- Matara",
                "    Matara Police Station",
                "- Hambantota",
                "    Hambantota Police Station",
                "Eastern Province",
                "- Batticaloa",
                "    Batticaloa Police Station",
                "- Trincomalee",
                "    Trincomalee Police Station",
                "- Ampara",
                "    Ampara Police Station",
                "Northern Province",
                "- Jaffna",
                "    Jaffna Police Station",
                "- Vavuniya",
                "    Vavuniya Police Station",
                "- Mannar",
                "    Mannar Police Station",
                "North Western Province",
                "- Kurunegala",
                "    Kurunegala Police Station",
                "- Puttalam",
                "    Puttalam Police Station",
                "North Central Province",
                "- Anuradhapura",
                "    Anuradhapura Police Station",
                "- Polonnaruwa",
                "    Polonnaruwa Police Station",
                "Uva Province",
                "- Badulla",
                "    Badulla Police Station",
                "- Monaragala",
                "    Monaragala Police Station",
                "Sabaragamuwa Province",
                "- Ratnapura",
                "    Ratnapura Police Station",
                "- Kegalle",
                "    Kegalle Police Station"
        );

        caseStautsCombo.getItems().addAll(
                "Open",
                "Closed",
                "Pending"
        );

        prioritybox.getItems().addAll(
                "High",
                "Medium",
                "Low"
        );
    }

    public void onBack() {
        // Get the current BorderPane from the Scene
        BorderPane menuParent = (BorderPane) backBtn.getScene().getRoot();

        // Set the center of the BorderPane to the CrimeCaseView
        menuParent.setCenter(Model.getInstance().getViewFactory().ShowCrimeCaseView());
    }

    public void onSave() {
        if(casevalidateInputs()) {

            String officerName = officerNametxt.getText();
            String officerBadgeNUm = OfficerBadgeNumber.getText();
            String policeStation = (String) policeStationcombo.getValue();
            String criminalNameValue = criminalName.getText();
            String criminalNicValue = criminalNic.getText();
            String crimeDateValue = cirmeDate.getValue() != null ? cirmeDate.getValue().toString() : "";
            String caseTypeValue = caseType.getValue();
            String subTypeValue = subType.getValue();
            String stageofCaseValue = stageofCase.getValue();
            String priority = prioritybox.getValue();
            String locationValue = locationTxt.getText();
            String filingNumValue = filingNum.getText();
            String filingDateValue = filingDate.getValue() != null ? filingDate.getValue().toString() : "";
            String firstHearingDateValue = fhearingDate.getValue() != null ? fhearingDate.getValue().toString() : "";
            String descriptionValue = discriptiontxt.getText();
            String caseStatusValue = caseStautsCombo.getValue();

            boolean caseInserted = Model.getInstance().getDatabaseDriver().CaseDetails(caseTypeValue, subTypeValue,
                    stageofCaseValue, priority, locationValue, filingNumValue, filingDateValue, firstHearingDateValue,
                    descriptionValue, caseStatusValue);

            boolean officerInserted = Model.getInstance().getDatabaseDriver().officerDetails(officerName, officerBadgeNUm, policeStation
                    ,criminalNameValue, criminalNicValue, crimeDateValue);

            if (caseInserted && officerInserted) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Case details saved successfully!");
                officerNametxt.setText("");
                criminalName.setText("");
                criminalNic.setText("");
                filingNum.setText("");
                locationTxt.setText("");
                discriptiontxt.setText("");
                OfficerBadgeNumber.setText("");

                caseType.getSelectionModel().select(-1);
                subType.getSelectionModel().select(-1);
                stageofCase.getSelectionModel().select(-1);
                caseStautsCombo.getSelectionModel().select(-1);
                prioritybox.getSelectionModel().select(-1);
                policeStationcombo.getSelectionModel().select(-1);

                cirmeDate.setValue(null);
                fhearingDate.setValue(null);
                fhearingDate.setValue(null);
                filingDate.setValue(null);

            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save case details.");
            }
        }
    }

    private boolean casevalidateInputs(){

        if (caseType.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Case Type is required.");
            return false;
        }

        if (subType.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Sub Type is required.");
            return false;
        }

        if (stageofCase.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Stage of Case is required.");
            return false;
        }

        if (prioritybox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Priority is required.");
            return false;
        }

        if (locationTxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Location is required.");
            return false;
        }

        if (filingNum.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Filing Number is required.");
            return false;
        }

        if (filingDate.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Filing Date is required.");
            return false;
        }

        if (fhearingDate.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "First Hearing Date is required.");
            return false;
        }

        if (discriptiontxt.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Description is required.");
            return false;
        }

        if (caseStautsCombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Case Status is required.");
            return false;
        }

        if(officerNametxt.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Error","in charge of the case required");
            return false;
        }
        if (OfficerBadgeNumber.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Officer Badge Number is required.");
            return false;
        }

        if (policeStationcombo.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Police Station is required.");
            return false;
        }

        if (criminalName.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Criminal Name is required.");
            return false;
        }

        if (criminalNic.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Criminal NIC is required.");
            return false;
        }

        if (cirmeDate.getValue()==null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Crime Date is required.");
            return false;
        }
        return true;
    }

    public void onCancel(){

        officerNametxt.setText("");
        criminalName.setText("");
        criminalNic.setText("");
        filingNum.setText("");
        locationTxt.setText("");
        discriptiontxt.setText("");
        OfficerBadgeNumber.setText("");

        caseType.getSelectionModel().select(-1);
        subType.getSelectionModel().select(-1);
        stageofCase.getSelectionModel().select(-1);
        caseStautsCombo.getSelectionModel().select(-1);
        prioritybox.getSelectionModel().select(-1);

        cirmeDate.setValue(null);
        fhearingDate.setValue(null);
        fhearingDate.setValue(null);
        filingDate.setValue(null);

    }

}

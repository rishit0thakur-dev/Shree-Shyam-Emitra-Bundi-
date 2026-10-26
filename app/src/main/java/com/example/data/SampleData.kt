package com.example.data

import com.example.model.*

object SampleData {

    val emitraServices = listOf(
        EmitraService(
            id = "reet_2026",
            titleHi = "रीट 2026 शिक्षक पात्रता परीक्षा (REET Level 1 & 2)",
            titleEn = "REET 2026 Teacher Eligibility Test",
            category = EmitraCategory.GOVT_VACANCY,
            department = "माध्यमिक शिक्षा बोर्ड राजस्थान (RBSE)",
            govtFee = 550,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "15 नवंबर 2026",
            eligibility = "12th + BSTC / D.El.Ed (Level 1) या Graduation + B.Ed (Level 2)",
            ageLimit = "न्यूनतम 18 वर्ष, अधिकतम नियमानुसार छूट",
            officialPortal = "https://education.rajasthan.gov.in",
            description = "राजस्थान प्राथमिक एवं उच्च प्राथमिक विद्यालय अध्यापक सीधी भर्ती के लिए अनिवार्य पात्रता परीक्षा। दोनों लेवल या एकल लेवल के लिए आवेदन करें।",
            highlights = listOf("लेवल 1 व 2 दोनों विकल्प उपलब्ध", "फॉर्म भरते ही रसीद प्राप्त करें", "त्रुटि रहित फॉर्म गारंटी"),
            isHotVacancy = true,
            requiredDocuments = listOf(
                DocumentItem("10वीं की अंकतालिका (Marksheet)", true, "जन्म तिथि सत्यापन हेतु"),
                DocumentItem("12वीं एवं स्नातक अंकतालिका", true, "शैक्षणिक योग्यता हेतु"),
                DocumentItem("BSTC / D.El.Ed या B.Ed प्रमाण पत्र", true, "प्रशिक्षण योग्यता"),
                DocumentItem("आधार कार्ड (Aadhaar Card)", true, "ओटीपी सत्यापन के लिए"),
                DocumentItem("पासपोर्ट साइज रंगीन फोटो (Photo)", true, "सफेद या हल्का बैकग्राउंड"),
                DocumentItem("हस्ताक्षर (Signature on White Paper)", true, "काली या नीली स्याही"),
                DocumentItem("जाति प्रमाण पत्र (Caste Certificate)", false, "आरक्षण लाभ के लिए यदि लागू हो"),
                DocumentItem("जन आधार कार्ड (Jan Aadhaar)", true, "राजस्थान के मूल निवासियों हेतु")
            )
        ),
        EmitraService(
            id = "police_constable",
            titleHi = "राजस्थान पुलिस कांस्टेबल भर्ती 2026",
            titleEn = "Rajasthan Police Constable Recruitment 2026",
            category = EmitraCategory.GOVT_VACANCY,
            department = "राजस्थान पुलिस विभाग (Rajasthan Police)",
            govtFee = 600,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "30 नवंबर 2026",
            eligibility = "12वीं पास + CET (समान पात्रता परीक्षा) उत्तीर्ण",
            ageLimit = "18 से 24 वर्ष (आरक्षित वर्गों को 5 वर्ष की छूट)",
            officialPortal = "https://police.rajasthan.gov.in",
            description = "राजस्थान पुलिस में कांस्टेबल (सामान्य/चालक/बैंड/घुड़सवार) के पदों पर बंपर भर्ती। बूँदी जिले हेतु भी रिक्तियां उपलब्ध।",
            highlights = listOf("CET स्कोर कार्ड अनिवार्य", "चालक पद हेतु ड्राइविंग लाइसेंस आवश्यक"),
            isHotVacancy = true,
            requiredDocuments = listOf(
                DocumentItem("CET 12th Level स्कोर कार्ड", true, "पात्रता जांच हेतु"),
                DocumentItem("10वीं व 12वीं की मार्कशीट", true, "शैक्षणिक दस्तावेज"),
                DocumentItem("आधार कार्ड (Aadhaar Card)", true, "आईडी प्रूफ"),
                DocumentItem("जन आधार कार्ड (Jan Aadhaar)", true, "परिवार विवरण"),
                DocumentItem("जाति प्रमाण पत्र (SC/ST/OBC/EWS)", true, "नवीनतम सक्षम अधिकारी द्वारा जारी"),
                DocumentItem("मूल निवास प्रमाण पत्र (Bonafide / Domicile)", true, "राजस्थान निवासी प्रमाण"),
                DocumentItem("नवीनतम पासपोर्ट साइज फोटो व हस्ताक्षर", true, "स्पष्ट फोटो"),
                DocumentItem("ड्राइविंग लाइसेंस (Light/Heavy)", false, "केवल चालक पद हेतु आवश्यक")
            )
        ),
        EmitraService(
            id = "patwari_bharti",
            titleHi = "राजस्थान पटवारी भर्ती (Rajasthan Patwari Vacancy)",
            titleEn = "Rajasthan Patwari Recruitment",
            category = EmitraCategory.GOVT_VACANCY,
            department = "राजस्थान कर्मचारी चयन बोर्ड (RSMSSB)",
            govtFee = 600,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "20 दिसंबर 2026",
            eligibility = "स्नातक (Graduation) + RSCIT / कम्प्यूटर डिप्लोमा",
            ageLimit = "18 से 40 वर्ष",
            officialPortal = "https://rsmssb.rajasthan.gov.in",
            description = "राजस्व विभाग में पटवारी के विभिन्न पदों पर भर्ती। एकबारीय पंजीयन (OTR) के माध्यम से ऑनलाइन आवेदन।",
            highlights = listOf("RSCIT कम्प्यूटर प्रमाण पत्र अनिवार्य", "OTR पंजीयन शुल्क छूट लागू"),
            isHotVacancy = true,
            requiredDocuments = listOf(
                DocumentItem("स्नातक डिग्री या फाइनल ईयर मार्कशीट", true, "योग्यता प्रमाण"),
                DocumentItem("RSCIT प्रमाण पत्र या कम्प्यूटर डिग्री", true, "अनिवार्य तकनीकी योग्यता"),
                DocumentItem("10वीं की अंकतालिका", true, "नाम व जन्मतिथि सत्यापन"),
                DocumentItem("आधार कार्ड व एसएसओ आईडी (SSO ID)", true, "पोर्टल लॉगिन हेतु"),
                DocumentItem("जाति व मूल निवास प्रमाण पत्र", true, "आरक्षण हेतु"),
                DocumentItem("फोटो व हस्ताक्षर", true, "स्कैन की जाने वाली प्रति")
            )
        ),
        EmitraService(
            id = "bstc_predled",
            titleHi = "BSTC (Pre D.El.Ed) 2026 प्रवेश परीक्षा",
            titleEn = "BSTC Pre D.El.Ed Entrance Exam 2026",
            category = EmitraCategory.GOVT_VACANCY,
            department = "प्रारंभिक शिक्षा विभाग राजस्थान",
            govtFee = 500,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "10 दिसंबर 2026",
            eligibility = "12वीं पास (न्यूनतम 50% सामान्य, 45% आरक्षित वर्ग)",
            ageLimit = "अधिकतम 28 वर्ष",
            officialPortal = "https://predeledraj2026.in",
            description = "दो वर्षीय शिक्षक प्रशिक्षण पाठ्यक्रम में प्रवेश हेतु राजस्थान राज्य स्तरीय प्री डी.एल.एड परीक्षा।",
            highlights = listOf("शिक्षक बनने का पहला कदम", "12वीं अपीयरिंग छात्र भी पात्र"),
            isHotVacancy = false,
            requiredDocuments = listOf(
                DocumentItem("10वीं व 12वीं की मार्कशीट", true, "अंक विवरण"),
                DocumentItem("आधार कार्ड", true, "पहचान पत्र"),
                DocumentItem("जन आधार कार्ड", true, "परिवार सत्यापन"),
                DocumentItem("पासपोर्ट साइज फोटो व हस्ताक्षर", true, "आवेदन हेतु"),
                DocumentItem("जाति प्रमाण पत्र", false, "वर्ग आरक्षण हेतु")
            )
        ),
        EmitraService(
            id = "jan_aadhar",
            titleHi = "जन आधार कार्ड (नया नामांकन / नाम जोड़ना / ई-केवाईसी)",
            titleEn = "Jan Aadhaar Card New / Update / e-KYC",
            category = EmitraCategory.CITIZEN_SERVICE,
            department = "आयोजना विभाग, राजस्थान सरकार",
            govtFee = 0,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "कोई अंतिम तिथि नहीं (सदा चालू)",
            eligibility = "राजस्थान के सभी स्थायी निवासी परिवार",
            ageLimit = "मुखिया महिला (18+ वर्ष) या परिवार का वरिष्ठ",
            officialPortal = "https://janaadhaar.rajasthan.gov.in",
            description = "राजस्थान सरकार की सभी जनकल्याणकारी योजनाओं, चिरंजीवी, राशन व पेंशन का मुख्य आधार कार्ड। सभी सदस्यों की बायोमेट्रिक व आधार ई-केवाईसी करवाएं।",
            highlights = listOf("सभी सदस्यों की ई-केवाईसी उपलब्ध", "नया नाम व संशोधन 24 घंटे में"),
            isHotVacancy = false,
            requiredDocuments = listOf(
                DocumentItem("परिवार के सभी सदस्यों के आधार कार्ड", true, "आधार लिंक मोबाइल नंबर सहित"),
                DocumentItem("महिला मुखिया की बैंक पासबुक", true, "खाता संख्या व IFSC कोड"),
                DocumentItem("विवाह प्रमाण पत्र (शादीशुदा सदस्यों हेतु)", false, "यदि उपलब्ध हो"),
                DocumentItem("बिजली का बिल या राशन कार्ड", false, "पते के प्रमाण हेतु"),
                DocumentItem("मोबाइल नंबर (ओटीपी प्राप्ति हेतु)", true, "सक्रिय नंबर")
            )
        ),
        EmitraService(
            id = "mool_niwas",
            titleHi = "मूल निवास प्रमाण पत्र (Bonafide / Domicile Certificate)",
            titleEn = "Domicile Certificate Rajasthan",
            category = EmitraCategory.CITIZEN_SERVICE,
            department = "राजस्व विभाग राजस्थान सरकार",
            govtFee = 40,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "सदा चालू (Never expires)",
            eligibility = "राजस्थान में न्यूनतम 10 वर्षों से निवासित व्यक्ति",
            ageLimit = "किसी भी आयु वर्ग हेतु",
            officialPortal = "https://emitra.rajasthan.gov.in",
            description = "सरकारी भर्तियों, छात्रवृत्ति एवं कॉलेजों में प्रवेश हेतु अनिवार्य डिजिटल हस्ताक्षरित मूल निवास प्रमाण पत्र।",
            highlights = listOf("तहसीलदार द्वारा डिजिटल हस्ताक्षरित", "आजीवन मान्य प्रमाण पत्र"),
            isHotVacancy = false,
            requiredDocuments = listOf(
                DocumentItem("जन आधार कार्ड व आधार कार्ड", true, "अनिवार्य पहचान"),
                DocumentItem("10 वर्ष पुराना निवास प्रमाण (मतदाता सूची/बिजली बिल/जमाबंदी)", true, "स्थायी निवास हेतु"),
                DocumentItem("शिक्षा प्रमाण पत्र (कक्षा 1 से 10वीं तक की अंकतालिकाएं)", true, "अध्ययन प्रमाण"),
                DocumentItem("पिता का पहचान पत्र व वोटर कार्ड", true, "पैतृक निवास"),
                DocumentItem("दो उत्तरदायी व्यक्तियों (गवाह) के हस्ताक्षर वाला फॉर्म", true, "श्री श्याम ई-मित्र पर फॉर्म उपलब्ध"),
                DocumentItem("पासपोर्ट साइज फोटो", true, "सत्यापित फोटो")
            )
        ),
        EmitraService(
            id = "jati_praman_patra",
            titleHi = "जाति प्रमाण पत्र (SC / ST / OBC / EWS / MBC)",
            titleEn = "Caste Certificate Rajasthan",
            category = EmitraCategory.CITIZEN_SERVICE,
            department = "सामाजिक न्याय एवं अधिकारिता विभाग",
            govtFee = 40,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "सदा चालू",
            eligibility = "संबंधित आरक्षित वर्ग का राजस्थान निवासी",
            ageLimit = "सभी आयु वर्ग",
            officialPortal = "https://sje.rajasthan.gov.in",
            description = "राज्य एवं केंद्र सरकार की नौकरियों, छात्रवृत्ति एवं आरक्षण लाभ हेतु डिजिटल जाति प्रमाण पत्र। ईडब्ल्यूएस व ओबीसी क्रीमीलेयर/नॉन-क्रीमीलेयर सुविधा।",
            highlights = listOf("डिजिटल साइन टोकन सहित", "ईडब्ल्यूएस आय प्रमाण पत्र साथ"),
            isHotVacancy = false,
            requiredDocuments = listOf(
                DocumentItem("जन आधार कार्ड व आधार कार्ड", true, "पहचान"),
                DocumentItem("सक्षम अधिकारी द्वारा प्रमाणित आय प्रमाण पत्र (Income Cert)", true, "OBC/EWS हेतु"),
                DocumentItem("पिता या दादा की 1984/1993 पूर्व की भूमि जमाबंदी या जाति रिकॉर्ड", true, "जाति सत्यता हेतु"),
                DocumentItem("राशन कार्ड व बिजली बिल", true, "परिवार सत्यापन"),
                DocumentItem("दो राजपत्रित अधिकारियों द्वारा प्रमाणित आवेदन प्रपत्र", true, "श्री श्याम ई-मित्र पर तैयार करवाएं")
            )
        ),
        EmitraService(
            id = "pension_verification",
            titleHi = "सामाजिक सुरक्षा पेंशन वार्षिक सत्यापन (Pension e-KYC)",
            titleEn = "Social Security Pension Annual Verification",
            category = EmitraCategory.CITIZEN_SERVICE,
            department = "सामाजिक सुरक्षा विभाग राजस्थान",
            govtFee = 0,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "31 दिसंबर तक वार्षिक अनिवार्य",
            eligibility = "वृद्धावस्था, विधवा, एकल नारी एवं दिव्यांग पेंशनभोगी",
            ageLimit = "पेंशनर धारक",
            officialPortal = "https://ssp.rajasthan.gov.in",
            description = "हर वर्ष पेंशन चालू रखने के लिए बायोमेट्रिक फिंगरप्रिंट या आइरिस/फेस ऑथेंटिकेशन द्वारा जीवित प्रमाण पत्र सत्यापन।",
            highlights = listOf("दुकान पर बायोमेट्रिक फिंगर मशीन उपलब्ध", "तुरंत रसीद निकाल कर दी जाती है"),
            isHotVacancy = false,
            requiredDocuments = listOf(
                DocumentItem("पेंशन पीपीओ नंबर (PPO Number) या जन आधार", true, "पेंशन पहचान"),
                DocumentItem("आधार कार्ड", true, "बायोमेट्रिक मिलान हेतु"),
                DocumentItem("पेंशनभोगी की व्यक्तिगत उपस्थिति (Fingerprint हेतु)", true, "अंगूठा लगाने के लिए"),
                DocumentItem("बैंक पासबुक", false, "खाता सत्यापन हेतु")
            )
        ),
        EmitraService(
            id = "scholarship_form",
            titleHi = "उत्तर मैट्रिक छात्रवृत्ति 2025-26 (Post Matric Scholarship)",
            titleEn = "Post Matric Scholarship Rajasthan",
            category = EmitraCategory.CITIZEN_SERVICE,
            department = "सामाजिक न्याय एवं अधिकारिता विभाग",
            govtFee = 0,
            emitraFee = ShopInfo.EMITRA_FORM_CHARGE,
            lastDate = "31 जनवरी 2027",
            eligibility = "11वीं, 12वीं, कॉलेज, आईटीआई, पॉलिटेक्निक एवं डिग्री छात्र (SC/ST/OBC/EBC)",
            ageLimit = "विद्यार्थी",
            officialPortal = "https://sjmsnew.rajasthan.gov.in",
            description = "कॉलेज एवं तकनीकी शिक्षण संस्थानों की शिक्षण शुल्क (फीस) की पूर्ण वापसी व छात्रवृत्ति आवेदन।",
            highlights = listOf("कॉलेज फीस रसीद अनिवार्य", "सीधे छात्र के बैंक खाते में राशि"),
            isHotVacancy = false,
            requiredDocuments = listOf(
                DocumentItem("कॉलेज/स्कूल की फीस जमा रसीद (Fee Receipt)", true, "मूल रसीद"),
                DocumentItem("गत वर्ष की उत्तीर्ण अंकतालिका (Marksheet)", true, "अंतिम कक्षा"),
                DocumentItem("जन आधार कार्ड व आधार कार्ड", true, "बैंक खाता जनआधार से जुड़ा हो"),
                DocumentItem("जाति एवं मूल निवास प्रमाण पत्र", true, "डिजिटल टोकन वाला"),
                DocumentItem("पिता का आय प्रमाण पत्र (Income Certificate)", true, "प्रपत्र 'I' या 'K'"),
                DocumentItem("10वीं की अंकतालिका", true, "जन्मतिथि हेतु")
            )
        )
    )

    val repairServices = listOf(
        RepairService(
            id = "screen_replacement",
            titleHi = "स्क्रीन व फोल्डर रिप्लेसमेंट (Display Folder)",
            titleEn = "Touch Screen & Display Folder Replacement",
            estPrice = "₹999 - ₹2800",
            turnaroundTime = "30 से 60 मिनट में तैयार",
            warrantyText = "3 महीने की टच गारंटी",
            features = listOf("Original & Premium First Copy", "Gorilla Glass प्रोटेक्शन", "फ्री टेम्पर्ड ग्लास साथ में"),
            iconName = "screen"
        ),
        RepairService(
            id = "battery_replacement",
            titleHi = "ओरिजिनल मोबाइल बैटरी (Battery Replacement)",
            titleEn = "High Capacity Battery Replacement",
            estPrice = "₹499 - ₹1200",
            turnaroundTime = "20 मिनट में चेंज",
            warrantyText = "6 महीने की रिप्लेसमेंट वारंटी",
            features = listOf("100% प्योर कोबाल्ट सेल", "फुल डे बैकअप", "हीटिंग व ड्रेनिंग समस्या का हल"),
            iconName = "battery"
        ),
        RepairService(
            id = "charging_jack",
            titleHi = "चार्जिंग जैक व Type-C पोर्ट रिपेयर",
            titleEn = "Charging Jack & Type-C Port Repair",
            estPrice = "₹150 - ₹450",
            turnaroundTime = "15 से 30 मिनट",
            warrantyText = "30 दिन की टेस्टिंग गारंटी",
            features = listOf("ओरिजिनल माइक्रोफोन व चार्जिंग PCB", "फास्ट चार्जिंग 33W/65W सपोर्ट", "लूज पिन समाधान"),
            iconName = "charging"
        ),
        RepairService(
            id = "speaker_mic",
            titleHi = "ईयर स्पीकर, लाउडस्पीकर एवं माइक ठीक करवाना",
            titleEn = "Speaker, Ear Speaker & Mic Repair",
            estPrice = "₹150 - ₹350",
            turnaroundTime = "20 मिनट",
            warrantyText = "1 माह की वारंटी",
            features = listOf("कॉल में आवाज कम आना ठीक करें", "क्रिस्टल क्लियर साउंड", "ओरिजिनल लाउड रिंगर"),
            iconName = "audio"
        ),
        RepairService(
            id = "water_damage",
            titleHi = "पानी में गिरा मोबाइल रिपेयर (Water Damage Recovery)",
            titleEn = "Water Damaged Smartphone Revival",
            estPrice = "₹300 - ₹900",
            turnaroundTime = "1 से 2 घंटे",
            warrantyText = "सफल रिपेयर पर ही शुल्क",
            features = listOf("अल्ट्रासोनिक मदरबोर्ड डीप क्लीनिंग", "शॉर्टिंग रिमूवल", "डेटा सुरक्षित रखने का पूरा प्रयास"),
            iconName = "water"
        ),
        RepairService(
            id = "software_unlock",
            titleHi = "सॉफ्टवेयर फ्लैशिंग, हैंग ऑन लोगो व FRP अनलॉक",
            titleEn = "Software Flashing & FRP Lock Removal",
            estPrice = "₹200 - ₹500",
            turnaroundTime = "30 मिनट",
            warrantyText = "सॉफ्टवेयर स्टेबिलिटी गारंटी",
            features = listOf("पैटर्न/पासवर्ड अनलॉक (बिल सहित)", "लेटेस्ट एंड्रॉइड वर्जन अपडेट", "हैंग प्रॉब्लम फिक्स"),
            iconName = "software"
        )
    )

    val mobileAccessories = listOf(
        MobileAccessory(
            id = "acc_charger_65w",
            nameHi = "65W सुपर फास्ट Type-C चार्जर एडॉप्टर",
            nameEn = "65W Super Fast Type-C Charger",
            category = "चार्जर (Charger)",
            price = 549,
            originalPrice = 999,
            warranty = "6 माह की वारंटी",
            features = listOf("SuperVOOC, Dash, QC 4.0 सपोर्ट", "हीट प्रोटेक्शन चिप", "Type-C पोर्ट")
        ),
        MobileAccessory(
            id = "acc_cable_braided",
            nameHi = "मजबूत ब्रेडेड Type-C फास्ट केबल (6A / 65W)",
            nameEn = "6A Braided Fast Type-C Data Cable",
            category = "केबल (Cable)",
            price = 149,
            originalPrice = 349,
            warranty = "3 माह वारंटी",
            features = listOf("अनब्रेकेबल नायलॉन ब्रेडेड", "सुपर फास्ट 6A करंट", "1.2 मीटर लंबाई")
        ),
        MobileAccessory(
            id = "acc_tempered_glass",
            nameHi = "11D सुपर क्लियर टेम्पर्ड ग्लास (फ्री पेस्टिंग)",
            nameEn = "11D Super Clear Edge-to-Edge Tempered Glass",
            category = "स्क्रीन प्रोटेक्टर",
            price = 99,
            originalPrice = 249,
            warranty = "दुकान पर हाथो-हाथ फिटिंग",
            features = listOf("9H हार्डनेस स्क्रैच प्रूफ", "स्मूथ टच फीलिंग", "ओलेओफोबिक कोटिंग")
        ),
        MobileAccessory(
            id = "acc_neckband",
            nameHi = "हैवी बेस ब्लूटूथ वायरलेस नेकबैंड (40 घंटे बैटरी)",
            nameEn = "Heavy Bass Wireless Bluetooth Neckband 40H",
            category = "ऑडियो (Audio)",
            price = 499,
            originalPrice = 1199,
            warranty = "6 माह की रिप्लेसमेंट वारंटी",
            features = listOf("40 घंटे का नॉन-स्टॉप प्लेबैक", "मैग्नेटिक ऑटो ऑन/ऑफ", "ENC माइक फॉर क्लियर कॉल्स")
        ),
        MobileAccessory(
            id = "acc_powerbank",
            nameHi = "20000mAh फास्ट चार्जिंग पावर बैंक",
            nameEn = "20000mAh Dual Output Fast Power Bank",
            category = "पावर बैंक",
            price = 999,
            originalPrice = 1899,
            warranty = "1 वर्ष वारंटी",
            features = listOf("22.5W टू-वे फास्ट चार्जिंग", "LED डिजिटल डिस्प्ले", "डुअल USB + Type-C पोर्ट")
        ),
        MobileAccessory(
            id = "acc_back_cover",
            nameHi = "प्रीमियम सिलिकॉन मैट बैक कवर व कैमरा रिंग",
            nameEn = "Premium Matte Silicone Back Cover with Ring",
            category = "कवर (Cover)",
            price = 149,
            originalPrice = 299,
            warranty = "परफेक्ट फिटिंग",
            features = listOf("कैमरा लेंस बंपर प्रोटेक्शन", "एंटी-फिंगरप्रिंट ग्रिप", "सभी मॉडल्स के लिए उपलब्ध")
        )
    )

    val bundiPrivateJobs = listOf(
        LocalJob(
            id = "job_comp_operator",
            titleHi = "कम्प्यूटर ऑपरेटर एवं बिलिंग एग्जीक्यूटिव",
            titleEn = "Computer Operator & Billing Executive",
            companyOrShop = "श्री कृष्णा मार्बल एवं कोटा स्टोन ट्रेडर्स",
            locationInBundi = "कोटा रोड, बाईपास चौराहा, बूँदी (Bundi)",
            salaryText = "₹12,000 - ₹16,000 / महीना",
            jobType = "फुल टाइम (Full Time)",
            timings = "सुबह 9:30 AM से शाम 7:30 PM",
            qualification = "12वीं पास / स्नातक + Tally व Excel का ज्ञान",
            whatToDoHi = "दुकान पर ग्राहकों के जीएसटी बिल बनाना, माल की एंट्री करना, स्टॉक मेंटेन करना तथा पेमेंट्स का हिसाब-किताब रखना।",
            roleDuties = listOf(
                "Tally Prime या Marg सॉफ्टवेयर में सेल/परचेस बिल काटना",
                "एक्सेल में दैनिक बिक्री व स्टॉक रिकॉर्ड बनाना",
                "पार्टियों के खाते चेक करना व बकाया वसूली का फॉलोअप लेना"
            ),
            contactPerson = "राजेश जी शर्मा (डायरेक्टर)",
            contactPhone = "9352558211",
            whatsappPhone = "9352558211",
            isUrgent = true,
            vacanciesCount = 2
        ),
        LocalJob(
            id = "job_showroom_sales",
            titleHi = "शोरूम सेल्स बॉय / सेल्स गर्ल (कपड़ा एवं रेडीमेड)",
            titleEn = "Showroom Sales Executive (Textiles)",
            companyOrShop = "बूँदी क्लॉथ एम्पोरियम एवं फैशन हब",
            locationInBundi = "सदर बाजार, मुख्य मार्केट, बूँदी",
            salaryText = "₹9,500 - ₹14,000 + इंसेंटिव",
            jobType = "फुल टाइम",
            timings = "सुबह 10:00 AM से रात 8:30 PM",
            qualification = "10वीं / 12वीं पास (बोलचाल अच्छी हो)",
            whatToDoHi = "शोरूम में आने वाले ग्राहकों को साड़ियां, सूट, शूटिंग-शर्टिंग दिखाना, अच्छी तरह बात करना और काउंटर संभालना।",
            roleDuties = listOf(
                "ग्राहकों का स्वागत करना व उनकी पसंद के अनुसार कपड़े दिखाना",
                "स्टॉक को साफ-सुथरा व व्यवस्थित रैक में लगाना",
                "त्यौहारों व शादी सीजन में बिक्री टारगेट पूरा करना"
            ),
            contactPerson = "मुकेश जी जैन",
            contactPhone = "7014360220",
            whatsappPhone = "7014360220",
            isUrgent = true,
            vacanciesCount = 3
        ),
        LocalJob(
            id = "job_delivery_partner",
            titleHi = "डिलीवरी पार्टनर (कोरियर एवं ऑनलाइन पार्सल)",
            titleEn = "Delivery Partner (E-commerce / Courier)",
            companyOrShop = "बूँदी एक्सप्रेस लॉजिस्टिक्स (Flipkart/Delhivery Hub)",
            locationInBundi = "सिल्लौर रोड, चुंगी नाका के पास, बूँदी",
            salaryText = "₹14,000 - ₹21,000 + पेट्रोल भत्ता",
            jobType = "फुल टाइम / पार्ट टाइम",
            timings = "सुबह 8:30 AM से (पार्सल वितरण अनुसार)",
            qualification = "10वीं पास + स्वयं की बाइक व ड्राइविंग लाइसेंस",
            whatToDoHi = "बूँदी शहर एवं आसपास के गांवों में ग्राहकों को ऑनलाइन ऑर्डर किए पार्सल पहुंचाना और कैश ऑन डिलीवरी (COD) कलेक्ट करना।",
            roleDuties = listOf(
                "हब से प्रतिदिन 40-60 पैकेट लोड करना",
                "ग्राहकों को कॉल करके सही पते पर डिलीवरी देना",
                "स्मार्टफोन ऐप में डिलीवरी कन्फर्मेशन मार्क करना"
            ),
            contactPerson = "संजय जी चौधरी",
            contactPhone = "9352558211",
            whatsappPhone = "9352558211",
            isUrgent = true,
            vacanciesCount = 5
        ),
        LocalJob(
            id = "job_accountant",
            titleHi = "सीनियर अकाउंटेंट (जीएसटी एवं इन्वेंटरी)",
            titleEn = "Accountant (GST & Tally Prime)",
            companyOrShop = "श्री श्याम ऑटोमोबाइल डिस्ट्रीब्यूटर्स",
            locationInBundi = "खोजा गेट के पास, बूँदी",
            salaryText = "₹16,000 - ₹24,000 / महीना",
            jobType = "फुल टाइम",
            timings = "सुबह 10:00 AM से शाम 7:00 PM",
            qualification = "B.Com / M.Com + न्यूनतम 1 वर्ष का अकाउंटिंग अनुभव",
            whatToDoHi = "फर्म की समस्त बहीखाता प्रविष्टियां करना, जीएसटी रिटर्न की रिपोर्ट तैयार करना तथा बैंक रिकंसिलिएशन संभालना।",
            roleDuties = listOf(
                "दैनिक वाउचर, बैंक जमा व निकासी एंट्री Tally में दर्ज करना",
                "GSTR 1 व 3B हेतु डाटा तैयार करना",
                "सप्लायर व वेंडर पेमेंट्स का ऑडिट करना"
            ),
            contactPerson = "नरेश जी गुप्ता",
            contactPhone = "7014360220",
            whatsappPhone = "7014360220",
            isUrgent = false,
            vacanciesCount = 1
        ),
        LocalJob(
            id = "job_teacher",
            titleHi = "गणित व अंग्रेजी शिक्षक (कक्षा 6 से 10वीं)",
            titleEn = "Maths & English Teacher (Secondary)",
            companyOrShop = "सरस्वती बाल विद्या मंदिर सीनियर सेकेंडरी स्कूल",
            locationInBundi = "विकास नगर, देवपुरा, बूँदी",
            salaryText = "₹12,000 - ₹18,000 / महीना",
            jobType = "फुल टाइम (डे शिफ्ट)",
            timings = "सुबह 7:30 AM से दोपहर 1:30 PM",
            qualification = "B.Sc / B.A + B.Ed (या अंतिम वर्ष)",
            whatToDoHi = "कक्षा 6 से 10वीं तक के विद्यार्थियों को गणित और सामान्य अंग्रेजी पढ़ाना, गृहकार्य जांचना एवं मासिक टेस्ट लेना।",
            roleDuties = listOf(
                "सरल व रोचक तरीके से विषय समझाना",
                "कमजोर बच्चों पर विशेष ध्यान देना",
                "पेरेंट्स-टीचर्स मीटिंग में प्रगति रिपोर्ट साझा करना"
            ),
            contactPerson = "प्रधानाचार्य जी",
            contactPhone = "9352558211",
            whatsappPhone = "9352558211",
            isUrgent = true,
            vacanciesCount = 2
        ),
        LocalJob(
            id = "job_receptionist_clinic",
            titleHi = "हॉस्पिटल रिसेप्शनिस्ट व कंप्यूटर ऑपरेटर",
            titleEn = "Hospital Receptionist & OPD Billing",
            companyOrShop = "महावीर सुपर स्पेशलिटी क्लिनिक",
            locationInBundi = "सर्किल रोड, सर्किट हाउस के सामने, बूँदी",
            salaryText = "₹10,000 - ₹14,000 / महीना",
            jobType = "फुल टाइम (शिफ्ट अनुसार)",
            timings = "सुबह 8:30 AM से 2:30 PM अथवा शाम 3:00 से 9:00 PM",
            qualification = "12वीं पास / कॉलेज + बेसिक कम्प्यूटर",
            whatToDoHi = "मरीजों की पर्ची बनाना, डॉक्टरों का अपॉइंटमेंट समय देना, फोन कॉल्स अटेंड करना और ओपीडी फाइल तैयार करना।",
            roleDuties = listOf(
                "आने वाले मरीजों का रजिस्ट्रेशन करना व पर्ची काटना",
                "सॉफ्टवेयर में परामर्श शुल्क व जांच एंट्री करना",
                "विनम्रतापूर्वक मरीजों व तीमारदारों को मार्गदर्शन देना"
            ),
            contactPerson = "डॉ. वी. के. सोनी",
            contactPhone = "7014360220",
            whatsappPhone = "7014360220",
            isUrgent = false,
            vacanciesCount = 2
        ),
        LocalJob(
            id = "job_hotel_staff",
            titleHi = "होटल फ्रंट डेस्क एग्जीक्यूटिव व गेस्ट सर्विस",
            titleEn = "Hotel Front Desk & Guest Care",
            companyOrShop = "रॉयल हैरिटेज पैलेस होटल एंड रेस्टोरेंट",
            locationInBundi = "तारागढ़ फोर्ट रोड, बूँदी",
            salaryText = "₹11,000 - ₹15,000 + भोजन व रहने की सुविधा",
            jobType = "फुल टाइम",
            timings = "रोटेशनल शिफ्ट (8 घंटे)",
            qualification = "12वीं / ग्रेजुएट (हिंदी व सामान्य अंग्रेजी संवाद)",
            whatToDoHi = "पर्यटकों का चेक-इन/चेक-आउट करना, कमरों की बुकिंग संभालना, बूँदी के दर्शनीय स्थलों की जानकारी देना।",
            roleDuties = listOf(
                "विदेशी व देशी पर्यटकों का आत्मीय स्वागत करना",
                "होटल मैनेजमेंट सॉफ्टवेयर में रूम बिलिंग करना",
                "कमरों की स्वच्छता व रूम सर्विस समन्वय रखना"
            ),
            contactPerson = "होटल मैनेजर",
            contactPhone = "9352558211",
            whatsappPhone = "9352558211",
            isUrgent = false,
            vacanciesCount = 2
        )
    )
}

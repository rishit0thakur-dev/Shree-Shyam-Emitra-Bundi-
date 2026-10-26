package com.example.model

enum class EmitraCategory(val displayNameHi: String, val displayNameEn: String) {
    ALL("सभी सेवाएं", "All Services"),
    GOVT_VACANCY("सरकारी भर्तियां", "Govt Vacancies"),
    CITIZEN_SERVICE("ई-मित्र दस्तावेज / सेवाएं", "E-Mitra Services")
}

data class DocumentItem(
    val name: String,
    val isMandatory: Boolean = true,
    val hindiHint: String = ""
)

data class EmitraService(
    val id: String,
    val titleHi: String,
    val titleEn: String,
    val category: EmitraCategory,
    val department: String,
    val govtFee: Int,
    val emitraFee: Int,
    val lastDate: String,
    val eligibility: String,
    val ageLimit: String,
    val officialPortal: String,
    val requiredDocuments: List<DocumentItem>,
    val description: String,
    val highlights: List<String> = emptyList(),
    val isHotVacancy: Boolean = false
)

enum class ApplicationStatus(val titleHi: String, val titleEn: String, val colorHex: Long) {
    PENDING("प्राप्त हुआ", "Received", 0xFFF59E0B),
    PROCESSING("फॉर्म भरा जा रहा है", "Processing", 0xFF3B82F6),
    SUBMITTED("सफलतापूर्वक भरा गया", "Submitted", 0xFF10B981),
    READY_FOR_COLLECTION("प्रिंट तैयार / संपर्क करें", "Ready for Pickup", 0xFF8B5CF6)
}

data class ServiceApplication(
    val id: String,
    val serviceId: String,
    val serviceTitle: String,
    val applicantName: String,
    val fatherName: String,
    val phone: String,
    val aadharOrJanAadhar: String,
    val category: String,
    val notes: String,
    val checkedDocuments: List<String>,
    val totalFee: Int,
    val paymentMode: String, // "UPI_QR", "PAY_AT_SHOP"
    val utrNumber: String = "",
    val status: ApplicationStatus = ApplicationStatus.PENDING,
    val timestamp: Long = System.currentTimeMillis()
)

data class RepairService(
    val id: String,
    val titleHi: String,
    val titleEn: String,
    val estPrice: String,
    val turnaroundTime: String,
    val warrantyText: String,
    val features: List<String>,
    val iconName: String
)

data class RepairRequest(
    val id: String,
    val phoneBrand: String,
    val phoneModel: String,
    val issueType: String,
    val description: String,
    val customerName: String,
    val customerPhone: String,
    val visitType: String, // "SHOP_VISIT", "PICKUP_INQUIRY"
    val estCost: String,
    val status: ApplicationStatus = ApplicationStatus.PENDING,
    val timestamp: Long = System.currentTimeMillis()
)

data class MobileAccessory(
    val id: String,
    val nameHi: String,
    val nameEn: String,
    val category: String,
    val price: Int,
    val originalPrice: Int,
    val warranty: String,
    val features: List<String>,
    val inStock: Boolean = true
)

data class LocalJob(
    val id: String,
    val titleHi: String,
    val titleEn: String,
    val companyOrShop: String,
    val locationInBundi: String,
    val salaryText: String,
    val jobType: String, // Full Time / Part Time
    val timings: String,
    val qualification: String,
    val whatToDoHi: String,
    val roleDuties: List<String>,
    val contactPerson: String,
    val contactPhone: String,
    val whatsappPhone: String,
    val isUrgent: Boolean = true,
    val vacanciesCount: Int = 1
)

data class JobApplication(
    val id: String,
    val jobId: String,
    val jobTitle: String,
    val companyName: String,
    val applicantName: String,
    val phone: String,
    val bundiAddress: String,
    val qualification: String,
    val experience: String,
    val notes: String,
    val timestamp: Long = System.currentTimeMillis()
)

package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SampleData
import com.example.data.db.AppDatabase
import com.example.data.db.ApplicationEntity
import com.example.data.db.CustomJobEntity
import com.example.data.db.JobApplicationEntity
import com.example.data.db.RepairEntity
import com.example.model.*
import com.example.ui.components.AppTab
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val appDao = db.appDao()

    // Database flows
    val applications: StateFlow<List<ApplicationEntity>> = appDao.getAllApplications()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val repairs: StateFlow<List<RepairEntity>> = appDao.getAllRepairs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val jobApplications: StateFlow<List<JobApplicationEntity>> = appDao.getAllJobApplications()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val customJobs: StateFlow<List<CustomJobEntity>> = appDao.getAllCustomJobs()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Combined jobs list (Sample + user-posted custom jobs)
    val allJobs: StateFlow<List<LocalJob>> = combine(flowOf(SampleData.bundiPrivateJobs), customJobs) { sample, custom ->
        val convertedCustom = custom.map { c ->
            LocalJob(
                id = c.id,
                titleHi = c.titleHi,
                titleEn = c.titleEn,
                companyOrShop = c.companyOrShop,
                locationInBundi = c.locationInBundi,
                salaryText = c.salaryText,
                jobType = c.jobType,
                timings = c.timings,
                qualification = c.qualification,
                whatToDoHi = c.whatToDoHi,
                roleDuties = listOf(c.whatToDoHi),
                contactPerson = c.contactPerson,
                contactPhone = c.contactPhone,
                whatsappPhone = c.whatsappPhone,
                isUrgent = true,
                vacanciesCount = 1
            )
        }
        convertedCustom + sample
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SampleData.bundiPrivateJobs)

    // Navigation state
    private val _selectedTab = MutableStateFlow(AppTab.EMITRA)
    val selectedTab: StateFlow<AppTab> = _selectedTab.asStateFlow()

    // Language state
    private val _isHindi = MutableStateFlow(true)
    val isHindi: StateFlow<Boolean> = _isHindi.asStateFlow()

    // Dialog & Flow states
    private val _serviceForDetails = MutableStateFlow<EmitraService?>(null)
    val serviceForDetails: StateFlow<EmitraService?> = _serviceForDetails.asStateFlow()

    private val _serviceForApply = MutableStateFlow<EmitraService?>(null)
    val serviceForApply: StateFlow<EmitraService?> = _serviceForApply.asStateFlow()

    private val _showBookRepair = MutableStateFlow<String?>(null)
    val showBookRepair: StateFlow<String?> = _showBookRepair.asStateFlow()

    private val _jobForApply = MutableStateFlow<LocalJob?>(null)
    val jobForApply: StateFlow<LocalJob?> = _jobForApply.asStateFlow()

    private val _showPostJob = MutableStateFlow(false)
    val showPostJob: StateFlow<Boolean> = _showPostJob.asStateFlow()

    private val _showTracking = MutableStateFlow(false)
    val showTracking: StateFlow<Boolean> = _showTracking.asStateFlow()

    private val _receiptToShow = MutableStateFlow<ServiceApplication?>(null)
    val receiptToShow: StateFlow<ServiceApplication?> = _receiptToShow.asStateFlow()

    fun selectTab(tab: AppTab) {
        _selectedTab.value = tab
    }

    fun toggleLanguage() {
        _isHindi.value = !_isHindi.value
    }

    fun openServiceDetails(service: EmitraService) {
        _serviceForDetails.value = service
    }

    fun closeServiceDetails() {
        _serviceForDetails.value = null
    }

    fun openServiceApply(service: EmitraService) {
        _serviceForApply.value = service
    }

    fun closeServiceApply() {
        _serviceForApply.value = null
    }

    fun openBookRepair(preselectedIssue: String? = null) {
        _showBookRepair.value = preselectedIssue ?: ""
    }

    fun closeBookRepair() {
        _showBookRepair.value = null
    }

    fun openJobApply(job: LocalJob) {
        _jobForApply.value = job
    }

    fun closeJobApply() {
        _jobForApply.value = null
    }

    fun openPostJob() {
        _showPostJob.value = true
    }

    fun closePostJob() {
        _showPostJob.value = false
    }

    fun openTracking() {
        _showTracking.value = true
    }

    fun closeTracking() {
        _showTracking.value = false
    }

    fun showReceipt(receipt: ServiceApplication) {
        _receiptToShow.value = receipt
    }

    fun closeReceipt() {
        _receiptToShow.value = null
    }

    // Database Actions
    fun submitServiceApplication(app: ServiceApplication) {
        viewModelScope.launch {
            val entity = ApplicationEntity(
                id = app.id,
                serviceId = app.serviceId,
                serviceTitle = app.serviceTitle,
                applicantName = app.applicantName,
                fatherName = app.fatherName,
                phone = app.phone,
                aadharOrJanAadhar = app.aadharOrJanAadhar,
                category = app.category,
                notes = app.notes,
                checkedDocuments = app.checkedDocuments.joinToString(", "),
                totalFee = app.totalFee,
                paymentMode = app.paymentMode,
                utrNumber = app.utrNumber,
                status = app.status.name,
                timestamp = app.timestamp
            )
            appDao.insertApplication(entity)
            _serviceForApply.value = null
            _receiptToShow.value = app
        }
    }

    fun submitRepairRequest(repair: RepairRequest) {
        viewModelScope.launch {
            val entity = RepairEntity(
                id = repair.id,
                phoneBrand = repair.phoneBrand,
                phoneModel = repair.phoneModel,
                issueType = repair.issueType,
                description = repair.description,
                customerName = repair.customerName,
                customerPhone = repair.customerPhone,
                visitType = repair.visitType,
                estCost = repair.estCost,
                status = repair.status.name,
                timestamp = repair.timestamp
            )
            appDao.insertRepair(entity)
            _showBookRepair.value = null
            _showTracking.value = true
        }
    }

    fun submitJobApplication(jobApp: JobApplication) {
        viewModelScope.launch {
            val entity = JobApplicationEntity(
                id = jobApp.id,
                jobId = jobApp.jobId,
                jobTitle = jobApp.jobTitle,
                companyName = jobApp.companyName,
                applicantName = jobApp.applicantName,
                phone = jobApp.phone,
                bundiAddress = jobApp.bundiAddress,
                qualification = jobApp.qualification,
                experience = jobApp.experience,
                notes = jobApp.notes,
                timestamp = jobApp.timestamp
            )
            appDao.insertJobApplication(entity)
            _jobForApply.value = null
        }
    }

    fun submitCustomJob(job: CustomJobEntity) {
        viewModelScope.launch {
            appDao.insertCustomJob(job)
            _showPostJob.value = false
        }
    }
}

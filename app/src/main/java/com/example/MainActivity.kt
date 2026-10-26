package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.SampleData
import com.example.model.ApplicationStatus
import com.example.model.ServiceApplication
import com.example.ui.components.AppBottomNav
import com.example.ui.components.AppTab
import com.example.ui.components.AppTopBar
import com.example.ui.dialogs.*
import com.example.ui.screens.BundiJobsScreen
import com.example.ui.screens.EmitraScreen
import com.example.ui.screens.MobileRepairScreen
import com.example.ui.screens.ShopLocationScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen(viewModel: MainViewModel = viewModel()) {
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val isHindi by viewModel.isHindi.collectAsStateWithLifecycle()

    val applications by viewModel.applications.collectAsStateWithLifecycle()
    val repairs by viewModel.repairs.collectAsStateWithLifecycle()
    val allJobs by viewModel.allJobs.collectAsStateWithLifecycle()

    val serviceForDetails by viewModel.serviceForDetails.collectAsStateWithLifecycle()
    val serviceForApply by viewModel.serviceForApply.collectAsStateWithLifecycle()
    val showBookRepair by viewModel.showBookRepair.collectAsStateWithLifecycle()
    val jobForApply by viewModel.jobForApply.collectAsStateWithLifecycle()
    val showPostJob by viewModel.showPostJob.collectAsStateWithLifecycle()
    val showTracking by viewModel.showTracking.collectAsStateWithLifecycle()
    val receiptToShow by viewModel.receiptToShow.collectAsStateWithLifecycle()

    // Handle system back navigation to return to Home Tab
    BackHandler(enabled = selectedTab != AppTab.EMITRA) {
        viewModel.selectTab(AppTab.EMITRA)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                isHindi = isHindi,
                onToggleLanguage = { viewModel.toggleLanguage() },
                activeCount = applications.size + repairs.size,
                onOpenTracking = { viewModel.openTracking() },
                onOpenLocation = { viewModel.selectTab(AppTab.SHOP_LOCATION) }
            )
        },
        bottomBar = {
            AppBottomNav(
                selectedTab = selectedTab,
                onTabSelected = { viewModel.selectTab(it) },
                isHindi = isHindi
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedContent(
                targetState = selectedTab,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "ScreenTransition"
            ) { targetTab ->
                when (targetTab) {
                    AppTab.EMITRA -> {
                        EmitraScreen(
                            services = SampleData.emitraServices,
                            isHindi = isHindi,
                            onSelectServiceForDetails = { viewModel.openServiceDetails(it) },
                            onSelectServiceForApply = { viewModel.openServiceApply(it) }
                        )
                    }
                    AppTab.MOBILE_REPAIR -> {
                        MobileRepairScreen(
                            repairServices = SampleData.repairServices,
                            accessories = SampleData.mobileAccessories,
                            isHindi = isHindi,
                            onOpenBookRepair = { viewModel.openBookRepair(it) }
                        )
                    }
                    AppTab.BUNDI_JOBS -> {
                        BundiJobsScreen(
                            jobs = allJobs,
                            isHindi = isHindi,
                            onOpenPostJob = { viewModel.openPostJob() },
                            onOpenApplyJob = { viewModel.openJobApply(it) }
                        )
                    }
                    AppTab.SHOP_LOCATION -> {
                        ShopLocationScreen(isHindi = isHindi)
                    }
                }
            }
        }
    }

    // Modal Dialogs
    serviceForDetails?.let { service ->
        ServiceDetailSheet(
            service = service,
            isHindi = isHindi,
            onDismiss = { viewModel.closeServiceDetails() },
            onApplyClick = { viewModel.openServiceApply(service) }
        )
    }

    serviceForApply?.let { service ->
        ApplyFormDialog(
            service = service,
            isHindi = isHindi,
            onDismiss = { viewModel.closeServiceApply() },
            onSubmit = { viewModel.submitServiceApplication(it) }
        )
    }

    receiptToShow?.let { receipt ->
        ReceiptDialog(
            application = receipt,
            isHindi = isHindi,
            onDismiss = { viewModel.closeReceipt() }
        )
    }

    showBookRepair?.let { preselectedIssue ->
        BookRepairDialog(
            preselectedIssue = if (preselectedIssue.isBlank()) null else preselectedIssue,
            isHindi = isHindi,
            onDismiss = { viewModel.closeBookRepair() },
            onSubmit = { viewModel.submitRepairRequest(it) }
        )
    }

    jobForApply?.let { job ->
        ApplyJobDialog(
            job = job,
            isHindi = isHindi,
            onDismiss = { viewModel.closeJobApply() },
            onSubmit = { viewModel.submitJobApplication(it) }
        )
    }

    if (showPostJob) {
        PostJobDialog(
            isHindi = isHindi,
            onDismiss = { viewModel.closePostJob() },
            onSubmit = { viewModel.submitCustomJob(it) }
        )
    }

    if (showTracking) {
        TrackingDialog(
            applications = applications,
            repairs = repairs,
            isHindi = isHindi,
            onDismiss = { viewModel.closeTracking() },
            onViewReceipt = { viewModel.showReceipt(it) }
        )
    }
}

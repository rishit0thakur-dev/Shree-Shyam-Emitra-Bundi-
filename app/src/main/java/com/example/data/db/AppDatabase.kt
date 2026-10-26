package com.example.data.db

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "service_applications")
data class ApplicationEntity(
    @PrimaryKey val id: String,
    val serviceId: String,
    val serviceTitle: String,
    val applicantName: String,
    val fatherName: String,
    val phone: String,
    val aadharOrJanAadhar: String,
    val category: String,
    val notes: String,
    val checkedDocuments: String,
    val totalFee: Int,
    val paymentMode: String,
    val utrNumber: String,
    val status: String,
    val timestamp: Long
)

@Entity(tableName = "repair_requests")
data class RepairEntity(
    @PrimaryKey val id: String,
    val phoneBrand: String,
    val phoneModel: String,
    val issueType: String,
    val description: String,
    val customerName: String,
    val customerPhone: String,
    val visitType: String,
    val estCost: String,
    val status: String,
    val timestamp: Long
)

@Entity(tableName = "job_applications")
data class JobApplicationEntity(
    @PrimaryKey val id: String,
    val jobId: String,
    val jobTitle: String,
    val companyName: String,
    val applicantName: String,
    val phone: String,
    val bundiAddress: String,
    val qualification: String,
    val experience: String,
    val notes: String,
    val timestamp: Long
)

@Entity(tableName = "custom_jobs")
data class CustomJobEntity(
    @PrimaryKey val id: String,
    val titleHi: String,
    val titleEn: String,
    val companyOrShop: String,
    val locationInBundi: String,
    val salaryText: String,
    val jobType: String,
    val timings: String,
    val qualification: String,
    val whatToDoHi: String,
    val contactPerson: String,
    val contactPhone: String,
    val whatsappPhone: String,
    val timestamp: Long
)

@Dao
interface AppDao {
    @Query("SELECT * FROM service_applications ORDER BY timestamp DESC")
    fun getAllApplications(): Flow<List<ApplicationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApplication(app: ApplicationEntity)

    @Query("SELECT * FROM repair_requests ORDER BY timestamp DESC")
    fun getAllRepairs(): Flow<List<RepairEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepair(repair: RepairEntity)

    @Query("SELECT * FROM job_applications ORDER BY timestamp DESC")
    fun getAllJobApplications(): Flow<List<JobApplicationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobApplication(jobApp: JobApplicationEntity)

    @Query("SELECT * FROM custom_jobs ORDER BY timestamp DESC")
    fun getAllCustomJobs(): Flow<List<CustomJobEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomJob(job: CustomJobEntity)
}

@Database(
    entities = [
        ApplicationEntity::class,
        RepairEntity::class,
        JobApplicationEntity::class,
        CustomJobEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shree_shyam_emitra_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

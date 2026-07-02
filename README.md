<div align="center">

# 🩺 DoctorEase

### A Modern Android Doctor Appointment Booking Application

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Backend-Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Firestore](https://img.shields.io/badge/Database-Cloud%20Firestore-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Material Design](https://img.shields.io/badge/UI-Material%20Design%203-4285F4?style=for-the-badge&logo=materialdesign&logoColor=white)

*A modern Android application that enables users to discover doctors, book appointments, and manage appointments seamlessly using Firebase.*

</div>

---

# 📖 About

**DoctorEase** is an Android application developed using **Kotlin** and **Firebase** to simplify the doctor appointment booking process.

The application allows users to browse doctors, view their details, book appointments through dynamic time slots, and manage their appointments efficiently with a clean and intuitive Material Design interface.

---

# ✨ Features

## 🔐 Authentication
- Secure User Registration
- User Login
- Firebase Authentication
- Persistent Login Session
- Logout Functionality

## 👨‍⚕️ Doctor Module
- Browse Doctors
- Doctor Details Screen
- Search Doctors by Specialization
- View Hospital Information
- Doctor Ratings
- Consultation Fees
- Experience Details

## 📅 Appointment Booking
- Date Selection
- Dynamic Slot Generation
- Automatic Slot Availability Detection
- Double Booking Prevention
- Real-time Appointment Booking

## 📋 Appointment Management
- View My Appointments
- Appointment Status Tracking
- Cancel Appointment
- Confirmation Dialog
- Automatic Slot Release After Cancellation

## 🎨 User Interface
- Material Design 3
- Responsive Layout
- Modern Card-Based UI
- RecyclerView Lists
- Custom App Icon
- Clean User Experience

---

# 🛠 Tech Stack

| Technology | Description |
|------------|-------------|
| Kotlin | Android Development |
| Android Studio | IDE |
| Firebase Authentication | User Authentication |
| Cloud Firestore | Database |
| Material Design 3 | UI Components |
| RecyclerView | List Rendering |
| Git | Version Control |
| GitHub | Project Hosting |

---

# 📂 Project Structure

```
DoctorEase
│
├── Authentication
│   ├── LoginActivity
│   └── SignupActivity
│
├── Doctor Module
│   ├── DoctorListActivity
│   ├── DoctorDetailsActivity
│   ├── DoctorAdapter
│   └── Doctor Model
│
├── Appointment Module
│   ├── BookingActivity
│   ├── MyAppointmentsActivity
│   ├── AppointmentAdapter
│   └── Appointment Model
│
├── Firebase
│   ├── Authentication
│   └── Cloud Firestore
│
└── Resources
    ├── Layouts
    ├── Drawables
    └── Icons
```

---

# 🔥 Firebase Collections

### Doctors Collection

```
doctorId
name
specialization
hospital
experience
consultationFee
rating
startTime
endTime
slotDuration
workingDays
imageUrl
```

### Appointments Collection

```
id
userId
doctorId
doctorName
patientName
patientPhone
relation
appointmentDate
appointmentTime
status
createdAt
```

---

# 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/divyxnsh-u/DoctorEase.git
```

### 2. Open the Project

Open the project in **Android Studio**.

### 3. Connect Firebase

Add your own Firebase project and place the `google-services.json` file inside:

```
app/
```

### 4. Sync Gradle

Allow Android Studio to download all dependencies.

### 5. Run the Application

Connect an Android device or emulator and run the project.

---

# 💡 Future Enhancements

- 👤 User Profile Management
- 👨‍👩‍👧 Family Member Booking
- ❤️ Favorite Doctors
- 💳 Online Payment Integration
- 🔔 Push Notifications
- 📍 Google Maps Integration
- 🌙 Dark Mode
- ⭐ Doctor Reviews & Ratings
- 📹 Video Consultation
- 👨‍💼 Admin Dashboard

---

# 📚 Learning Outcomes

This project helped me gain hands-on experience with:

- Android App Development
- Kotlin Programming
- Firebase Authentication
- Cloud Firestore
- CRUD Operations
- RecyclerView
- Material Design 3
- Dynamic UI Generation
- Android View Binding
- Git & GitHub

---

# 👨‍💻 Developer

**Divyanshu Singh**

**B.Tech - Computer Science & Artificial Intelligence**  
**Indian Institute of Information Technology Lucknow**

### Connect with me

- **GitHub:** https://github.com/divyxnsh-u
- **LinkedIn:** https://www.linkedin.com/in/divyanshu-singh-603656328/

---

# ⭐ Support

If you found this project helpful, consider giving it a ⭐ on GitHub.

---

<div align="center">

**Made with ❤️ using Kotlin, Firebase & Material Design 3**

</div>
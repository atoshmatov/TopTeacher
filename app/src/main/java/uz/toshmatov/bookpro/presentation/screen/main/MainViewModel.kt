package uz.toshmatov.bookpro.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshmatov.bookpro.core.logger.logInfo
import uz.toshmatov.bookpro.data.remote.models.Teacher
import uz.toshmatov.bookpro.domain.repository.TeacherRepository

class MainViewModel(
    private val teacherRepository: TeacherRepository
) : ViewModel() {
    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val teacherList = ArrayList<Teacher>()

    init {
        getTeachers()
    }

    fun reduce(event: MainEvent) {
        when (event) {
            is MainEvent.SearchQuery -> {
                _state.update {
                    it.copy(searchQuery = event.query)
                }
                filter()
            }

            is MainEvent.UpdateTeacherRating -> {
                updateTeacherRating(event.teacherId, event.newRating)
            }
        }
    }

    private fun updateTeacherRating(teacherId: String, newRating: Float) {
        viewModelScope.launch {
            teacherRepository.updateTeacherRating(
                teacherId = teacherId,
                newRating = newRating
            ) { isSuccess, message ->
                logInfo { "isSuccess: $isSuccess, message: $message" }
                if (isSuccess) {
                    _state.update {
                        filter()
                        it.copy(
                            updateTeacherRating = true,
                            updateTeacherRatingMessage = "Rating updated successfully"
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            updateTeacherRating = false,
                            updateTeacherRatingMessage = message ?: "Rating update failed"
                        )
                    }
                }
            }
        }
    }

    private fun getTeachers() {
        viewModelScope.launch {
            _state.update { detailState ->
                detailState.copy(isLoading = true)
            }
            teacherRepository.getTeachers()
                .collectLatest { teachers ->
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    teachers.apply {
                        teacherList.clear()
                        teacherList.addAll(teachers)
                        filter()
                    }
                }
        }
    }

    private fun filter() {
        val query = _state.value.searchQuery
        if (query.isEmpty()) {
            _state.update {
                it.copy(
                    teachers = teacherList.toPersistentList()
                )
            }
            return
        }

        _state.update {
            it.copy(
                teachers = teacherList.filter { teacher ->
                    teacher.name?.contains(
                        query, ignoreCase = true
                    ) == true || teacher.subject?.contains(
                        query,
                        ignoreCase = true
                    ) == true
                }.toPersistentList()
            )
        }
    }

}

/*[
{
    "id": "1",
    "name": "Akmal Karimov",
    "subject": "Matematika",
    "university": "Toshkent Davlat Universiteti",
    "rating": 4.5
},
{
    "id": "2",
    "name": "Gulnora Rahimova",
    "subject": "Fizika",
    "university": "O‘zbekiston Milliy Universiteti",
    "rating": 4.8
},
{
    "id": "3",
    "name": "Javlonbek Rustamov",
    "subject": "Informatika",
    "university": "TATU",
    "rating": 4.2
},
{
    "id": "4",
    "name": "Nodira To‘xtayeva",
    "subject": "Kimyo",
    "university": "Toshkent Kimyo Texnologiya Instituti",
    "rating": 4.7
},
{
    "id": "5",
    "name": "Bekzod Hamidov",
    "subject": "Tarix",
    "university": "Toshkent Davlat Pedagogika Universiteti",
    "rating": 4.1
},
{
    "id": "6",
    "name": "Shahnoza Qodirova",
    "subject": "Biologiya",
    "university": "Buxoro Davlat Universiteti",
    "rating": 4.6
},
{
    "id": "7",
    "name": "Ulug‘bek Islomov",
    "subject": "Dasturlash",
    "university": "TATU",
    "rating": 4.9
},
{
    "id": "8",
    "name": "Dilshod Yoqubov",
    "subject": "Iqtisodiyot",
    "university": "Toshkent Davlat Iqtisodiyot Universiteti",
    "rating": 4.3
},
{
    "id": "9",
    "name": "Xurshid Ismoilov",
    "subject": "Ingliz tili",
    "university": "O‘zbekiston Davlat Jahon Tillari Universiteti",
    "rating": 4.8
},
{
    "id": "10",
    "name": "Malika Karimova",
    "subject": "Psixologiya",
    "university": "Toshkent Davlat Pedagogika Universiteti",
    "rating": 4.4
},
{
    "id": "11",
    "name": "Anvar Ro‘ziboyev",
    "subject": "Mexanika",
    "university": "Toshkent Texnika Universiteti",
    "rating": 4.2
},
{
    "id": "12",
    "name": "Oydinxon Sodiqova",
    "subject": "Huquqshunoslik",
    "university": "Toshkent Davlat Yuridik Universiteti",
    "rating": 4.7
},
{
    "id": "13",
    "name": "Ilyos Mirzayev",
    "subject": "Moliya",
    "university": "Toshkent Davlat Iqtisodiyot Universiteti",
    "rating": 4.5
},
{
    "id": "14",
    "name": "Saida Qayumova",
    "subject": "Jurnalistika",
    "university": "O‘zbekiston Milliy Universiteti",
    "rating": 4.3
},
{
    "id": "15",
    "name": "Rustam Sharipov",
    "subject": "Falsafa",
    "university": "Toshkent Davlat Universiteti",
    "rating": 4.0
},
{
    "id": "16",
    "name": "Dilbar Norboyeva",
    "subject": "Geografiya",
    "university": "Samarqand Davlat Universiteti",
    "rating": 4.6
},
{
    "id": "17",
    "name": "Doston Qosimov",
    "subject": "Astronomiya",
    "university": "Buxoro Davlat Universiteti",
    "rating": 4.8
},
{
    "id": "18",
    "name": "Shoxrux Ergashev",
    "subject": "Elektrotexnika",
    "university": "Toshkent Texnika Universiteti",
    "rating": 4.5
},
{
    "id": "19",
    "name": "Madina To‘laganova",
    "subject": "Pedagogika",
    "university": "Toshkent Davlat Pedagogika Universiteti",
    "rating": 4.7
},
{
    "id": "20",
    "name": "Umar Shodmonov",
    "subject": "San’at",
    "university": "Toshkent San’at Instituti",
    "rating": 4.4
}
]*/

/*
[
  {
    "email": "toshmatov_test@gmail.com",
    "name": "Toshmatov Alimardon",
    "password": "atoshmatov@Test",
    "student_id": "c67ec782-beab-4f75-ac91-a49007acc5ca",
    "university": "TATU"
  },
  {
    "email": "akramov_ali@gmail.com",
    "name": "Akramov Ali",
    "password": "akramovAli@Test123",
    "student_id": "d2f41eaa-12b3-47c6-92a7-561dc5b3f9e4",
    "university": "TATU"
  },
  {
    "email": "nurullayev_umar@gmail.com",
    "name": "Nurullayev Umar",
    "password": "nurUmar@Test789",
    "student_id": "3f6c1d4b-a74e-4b68-bd1e-8f2d8c1f7e22",
    "university": "TATU"
  },
  {
    "email": "sharipov_aziz@gmail.com",
    "name": "Sharipov Aziz",
    "password": "azizSharipov@Test",
    "student_id": "a9b3cdee-1f5e-432d-88b1-6d345f0e7c88",
    "university": "TATU"
  },
  {
    "email": "karimov_sardor@gmail.com",
    "name": "Karimov Sardor",
    "password": "sardorK@Test654",
    "student_id": "e4f5d6a8-bb47-4f91-92f0-3d06a9b6f78c",
    "university": "TATU"
  },
  {
    "email": "abduvohidov_murod@gmail.com",
    "name": "Abduvohidov Murod",
    "password": "murodA@Test456",
    "student_id": "1c2e3f4g-5h6i-7j8k-9l0m-n1o2p3q4r5s6",
    "university": "TATU"
  },
  {
    "email": "saidov_dilshod@gmail.com",
    "name": "Saidov Dilshod",
    "password": "dilshodS@Test234",
    "student_id": "f8e9d7c6-b5a4-3f21-98g7-h6i5j4k3l2m1",
    "university": "TATU"
  },
  {
    "email": "komilova_madina@gmail.com",
    "name": "Komilova Madina",
    "password": "madinaK@Test321",
    "student_id": "b3a2c1d4-e5f6-7g8h-9i0j-k1l2m3n4o5p6",
    "university": "TATU"
  },
  {
    "email": "rustamov_bekzod@gmail.com",
    "name": "Rustamov Bekzod",
    "password": "bekzodR@Test987",
    "student_id": "c6d5e4f3-b2a1-9g8h-7i6j-5k4l3m2n1o0p",
    "university": "TATU"
  },
  {
    "email": "usmonova_nargiza@gmail.com",
    "name": "Usmonova Nargiza",
    "password": "nargizaU@Test741",
    "student_id": "e9f8d7c6-b5a4-3g2h-1i0j-k9l8m7n6o5p4",
    "university": "TATU"
  }
]

* */

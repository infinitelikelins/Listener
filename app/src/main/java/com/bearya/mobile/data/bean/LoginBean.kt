package com.bearya.mobile.data.bean

data class UserBean(val id: Int,
                    val openId: String,
                    val unionId: String,
                    val avatar: String,
                    val nickname: String,
                    val gender: Int,
                    val uid: Int,
                    val mobile: String,
                    val vip: Int,
                    val subscribe: Int,
                    val is_vip: Int,
                    val expiration: String?)

data class BabyBean(val id: Int,
                    val name: String,
                    val avatar: String,
                    val gender: Int,
                    val birthday: Long,
                    val create_time: Long,
                    val user_id: Int,
                    val serial_num: String,
                    val imei: String,
                    val tags: String,
                    val relationship: String,
                    val is_vip_5: Int,
                    val vip_expiration_5: String?,
                    val is_default: Int,
                    val removed: Int,
                    val grade: Int,
                    val baby_id: Int,
                    val ages: String,
                    val age: String)

data class LoginBean(val user: UserBean, val token: String, val baby: BabyBean)
package com.bearya.mobile.data.repository

import com.bearya.mobile.component.Api
import com.bearya.mobile.data.bean.UserBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object UserRepository {

    val id: Int
        get() = kv.decodeInt("id", 0)

    val token: String
        get() = kv.decodeString("token", "") ?: ""

    val nickname: String?
        get() = kv.decodeString("nickname", null)

    val avatar: String?
        get() = kv.decodeString("avatar", null)

    val mobilePhone: String?
        get() = kv.decodeString("mobile", null)

    val isVip: Boolean
        get() = kv.decodeBool("is_vip", false)

    val isUnsigned : Boolean
        get() = token.isBlank()

    fun userLogin(scope: CoroutineScope,
                  mobile: String,
                  password: String,
                  successFunc: () -> Unit,
                  errorFunc: (Exception) -> Unit) {
        scope.launch {
            try {
                if (isUnsigned) {
                    val loginResult = Api.login(mobile, password)
                    saveUser(loginResult?.user, loginResult?.token)
                    successFunc.invoke()
                } else {
                    errorFunc.invoke(Exception("你已经登录了"))
                }
            } catch (ex: Exception) {
                errorFunc.invoke(ex)
            }
        }
    }

    fun userLogout(scope: CoroutineScope, successFunc: () -> Unit) {
        scope.launch {
            try {
                Api.logout(id, token)
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                clearUser()
                successFunc.invoke()
            }
        }
    }

    private fun clearUser() {
        kv.removeValuesForKeys(
                arrayOf("token", "id", "nickname", "avatar", "mobile", "is_vip", "gender", "openId",
                        "unionId", "uid", "vip", "expiration"))
    }

    private fun saveUser(user: UserBean?, token: String?) {
        kv.encode("token", token)
        kv.encode("id", user?.id ?: 0)
        kv.encode("nickname", user?.nickname)
        kv.encode("avatar", user?.avatar)
        kv.encode("mobile", user?.mobile)
        kv.encode("is_vip", user?.is_vip == 1)
        kv.encode("gender", user?.gender ?: 0)
        kv.encode("openId", user?.openId)
        kv.encode("unionId", user?.unionId)
        kv.encode("uid", user?.uid ?: 0)
        kv.encode("vip", user?.vip ?: 0)
        kv.encode("expiration", user?.expiration)
    }

}
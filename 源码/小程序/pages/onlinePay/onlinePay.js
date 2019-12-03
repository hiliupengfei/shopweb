// pages/onlinePay/onlinePay.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    thisCode : null 
  },
  onLoad: function (options) {
    
  },
  /**
   * 组件的方法列表
   */
  methods: {
    onGotUserInfo:function(){
      var that = this;
      wx.login({
        success: function (res) {
          that.thisCode = res.code;//登录凭证
          console.log(that.thisCode)
          if (res.code) {
            //2、调用获取用户信息接口
            // 查看是否授权
            wx.getUserInfo({
              success: function (res) {
                // that.globalData.encryptedData = res.encryptedData
                // that.globalData.iv = res.iv
                console.log('[INFO] app.js/ ', { encryptedData: res.encryptedData, iv: res.iv, code: that.thisCode })
                //3.请求自己的服务器，解密用户信息 获取unionId等加密信息
                wx.request({
                  url: 'http://localhost:8083/ylshop/users/decodeUserInfo.php',//自己的服务接口地址
                  method: 'get',
                  header: {
                    "Content-Type": "applciation/json"
                  },
                  data: { encryptedData: res.encryptedData, iv: res.iv, code: that.thisCode },
                  success: function (data) {

                    //4.解密成功后 获取自己服务器返回的结果
                    console.log(data.status)
                    if (data.status == 1) {
                      var userInfos = data.userInfo;
                      // that.globalData.openId = userInfos.openId;
                      console.log('[INFO] app.js/ userInfo:', userInfos)
                    } else {
                      console.log('[INFO] app.js/ 解密失败')
                    }
                  },
                  fail: function () {
                    console.log('[INFO] app.js/ 系统错误')
                  }
                })
              },
              fail: function () {
                console.log('[INFO] app.js/ 获取用户信息失败')
              }
            })
          } else {
            console.log('[INFO] app.js/ 获取用户登录态失败！' + r.errMsg)
          }
        },
        fail: function () {
          console.log('[INFO] app.js/ 登陆失败')
        }
      })

      // 获取用户信息
      // wx.getSetting({
      //   success: res => {
      //     if (res.authSetting['scope.userInfo']) {
      //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
      //       wx.getUserInfo({
      //         success: res => {
      //           // 可以将 res 发送给后台解码出 unionId
      //           this.globalData.userInfo = res.userInfo;

      //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      //           // 所以此处加入 callback 以防止这种情况
      //           if (this.userInfoReadyCallback) {
      //             this.userInfoReadyCallback(res)
      //           }
      //         }
      //       })
      //     }
      //   }
      // })


    }
      
  }
})

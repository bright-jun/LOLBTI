from django.urls import path
from . import views

app_name = 'userGameData'

urlpatterns = [
    path('test/', views.test),
    path('userinfo/<str:summonerName>', views.userInfo),
    path('recommend/mastery/<str:summonerName>', views.recommendByMastery),
<<<<<<< HEAD
    # path('update/mastery/<str:summonerName>', views.updateMastery),
=======
>>>>>>> be825aa26a3cc0799deb125e82b24303aa8c7099
]

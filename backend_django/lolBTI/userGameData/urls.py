from django.urls import path
from . import views

app_name = 'userGameData'

urlpatterns = [
    path('test/', views.test),
]

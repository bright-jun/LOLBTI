from django.shortcuts import render, get_object_or_404
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from django.http.response import JsonResponse, HttpResponse


@api_view(['GET'])
def test(request):
    testData = {1 : "testtesttesttest"}
    return JsonResponse(testData)



# tech


## Mimari
Bu proje, Clean Architecture ([Temiz Mimarlık]) prensiplerine dayalı olarak inşa edilmiştir. Temel mimari bileşenleri şunlardır:

Data Module: API ; ağ işlemleri için ayrı bir modül içerir. Domain-Impl : Domain baglantısı sağlanır ve ayrılarak domain implementasyonu kırılmıştır diğer modullerden. Persistence: Local işlemler için oluşturulmuş module.
Domain Module: Use case'ler ve domain modelleri içeren bağımsız bir modüldür.
Core Module: Temel işlemleri ve genel işlevleri içeren bağımsız bir modüldür.
Presentation Module: Farklı özelliklere sahip modülleri içerir ve modül tabanlı bir mimari kullanır.


## CI iş akışı 

- Her push işlemi veya master dalına yapılan pull request (çekme isteği) işlemleri tetikleyici olarak kullanılır.
- Ubuntu-Latest çalışma ortamı üzerinde aşağıdaki işlemler gerçekleştirilir:
    - Depo (repository) çekilir.
    - Java JDK 18 kurulumu yapılır.
    - Gradle ile proje derlenir.
    - Gradle ile testler çalıştırılır.

## Güvenli Anahtarlar 

Proje içindeki hassas bilgiler (örneğin, API anahtarları) BUILD_CONFIG dosyasında saklanır.
Bu dosya projenin güvenliğini artırmak ve hassas bilgilerin sızdırılmasını önlemek için git deposuna eklenmez. 
Aşağıda, BUILD_CONFIG dosyasına örnek bir bakış bulabilirsiniz:

```kotlin
object BuildConfig {
    const val BASE_URL = "https://xxx/"
}

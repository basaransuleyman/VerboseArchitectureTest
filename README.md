# tech


## Mimari
Clean Architecture prensiplerine dayalı olarak inşa edilmiştir. Temel mimari bileşenleri şunlardır:

Data Module: API ; ağ işlemleri için ayrı bir modül içerir. Domain-Impl : Domain baglantısı sağlanır ve ayrılarak domain implementasyonu kırılmıştır diğer modullerden. Persistence: Local işlemler için oluşturulmuş module.
Domain Module: Use case'ler ve domain modelleri içeren bağımsız bir modüldür.
Core Module: Temel işlemleri ve genel işlevleri içeren bağımsız bir modüldür.
Presentation Module: Farklı özelliklere sahip modülleri içerir ve modül tabanlı bir mimari kullanır.

## Reactive Programming ile Veri Akışı

Reactive programming prensiplerini kullanarak veri akışını yönetmek için Kotlin Flow'larını kullanır, State & Shared flow ve Clod flow. 

## CI iş akışı 

- Her push işlemi veya master dalına yapılan pull request (çekme isteği) işlemleri tetikleyici olarak kullanılır.
- Ubuntu-Latest çalışma ortamı üzerinde aşağıdaki işlemler gerçekleştirilir:
    - Depo (repository) çekilir.
    - Java JDK 18 kurulumu yapılır.
    - Gradle ile proje derlenir.
    - Gradle ile testler çalıştırılır.

## Performans İyileştirmeleri
### Context Kullanımı
- **Dikkatli Context Kullanımı**: Uygulama içinde Context kullanımına dikkat edilmiş ve gereksiz Context bağlantılarından kaçınılmıştır. Bu, gereksiz bellek tüketimini önler ve uygulamanın daha hızlı çalışmasını sağlar.

### Strong References Kontrolü
- **Strong References Kontrolü**: Güçlü (strong) referansların kullanımı, gereksiz bellek sızıntılarını önlemek için göz önünde bulundurulmuştur. Kritik veri yapıları ve nesneler, gereksiz güçlü referanslardan kaçınarak daha iyi yönetilir gerektiği yerde nullable tanımlanarak işlem sonucu null atanmıştır..

### Fragment Lifecycle'a Göre İşlemler
- **Fragment Lifecycle İzlemi**: Fragment'ların yaşam döngüsüne dikkat edilmiş ve uygun nullable işlemleri yapılmıştır. Bu, Fragment'lar arasındaki geçişler sırasında hata olasılığını azaltır ve uygulamanın daha kararlı çalışmasını sağlar.

## Dependency Catalog (Bağımlılık Kataloğu)

Projede kullanılan bağımlılıkların listesi ve sürümleri, `libs.versions.toml`  adlı bir TOML dosyasında toplanmıştır. Bu dosya, projede kullanılan bağımlılıkları ve sürümlerini içerir ve tek yerden yönetilir.

## Configuration Change

Bu proje, Android uygulamanızın veri yönetimi ve yaşam döngüsü yönetimi için View Model'leri kullanır. View Model'ler, verilerinizi etkili bir şekilde saklamak ve activity veya fragmentlar arasında paylaşmak için kullanılır. Ayrıca, yapılandırma değişiklikleri (örneğin ekran döndürme) sırasında verilerinizin korunmasına yardımcı olur.

## Güvenlik Anahtarlar 

Proje içindeki hassas bilgiler (örneğin, API anahtarları) BUILD_CONFIG dosyasında saklanır.
Bu dosya projenin güvenliğini artırmak ve hassas bilgilerin sızdırılmasını önlemek için git deposuna eklenmez. 
Aşağıda, BUILD_CONFIG dosyasına örnek bir bakış bulabilirsiniz:

```kotlin
object BuildConfig {
    const val BASE_URL = "https://xxx/"
}
 

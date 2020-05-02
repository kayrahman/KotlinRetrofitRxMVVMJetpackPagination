
import SwiftUI
import Combine

struct Course: Decodable {
    let name, imageUrl: String
}

class NetworkManager: ObservableObject {
    var didChange = PassthroughSubject<NetworkManager, Never>()
    
    var courses = [Course]() {
        didSet {
            didChange.send(self)
        }
    }
    
    init() {
        guard let url = URL(string: "https://api.letsbuildthatapp.com/jsondecodable/courses") else { return }
        URLSession.shared.dataTask(with: url) { (data, _, _) in
            
            guard let data = data else { return }
            
            let courses = try! JSONDecoder().decode([Course].self, from: data)
            DispatchQueue.main.async {
                self.courses = courses
            }
            
            print("completed fetching json")
            
        }.resume()
    }
}

struct ContentView : View {
    
    @State var networkManager = NetworkManager()
    
    var body: some View {
        NavigationView {
            
            ForEach(networkManager.courses){course in
                 CourseRowView(course: course)
            }
    
            List (
            networkManager.courses.identified(by: \.name)
            ) { course in
                CourseRowView(course: course)
                
            }.navigationBarTitle(Text("Courses"))
        }
    }
}

struct CourseRowView: View {
    let course: Course
    
    var body: some View {
        VStack (alignment: .leading) {
            ImageViewWidget(imageUrl: course.imageUrl)
//            Image("apple")
//                .resizable()
//                .frame(width: 200, height: 200)
//                .clipped()
            Text(course.name)
            Text(course.imageUrl)
        }
    }
}

class ImageLoader: ObservableObject {
    var didChange = PassthroughSubject<Data, Never>()
    
    var data = Data() {
        didSet {
            didChange.send(data)
        }
    }
    
    init(imageUrl: String) {
        // fetch image data and then call didChange
        guard let url = URL(string: imageUrl) else { return }
        URLSession.shared.dataTask(with: url) { (data, _, _) in
            guard let data = data else { return }
            
            DispatchQueue.main.async {
                self.data = data
            }
            
        }.resume()
    }
}

struct ImageViewWidget: View {
    
    @ObservedObject var imageLoader: ImageLoader
    
    init(imageUrl: String) {
        imageLoader = ImageLoader(imageUrl: imageUrl)
    }
    
    var body: some View {
        Image(uiImage: (imageLoader.data.count == 0) ? UIImage(named: "apple")! : UIImage(data: imageLoader.data)!)
            .resizable()
            .frame(width: 320, height: 180)
    }
}

#if DEBUG
struct ContentView_Previews : PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
#endif
